package com.ftn.OnlineFitness.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.OnlineFitness.dao.ReportDAO;
import com.ftn.OnlineFitness.model.Report;
import com.ftn.OnlineFitness.model.Trainer;

@Repository
public class ReportDAOImpl implements ReportDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class RowCallBackHandler implements RowCallbackHandler {
		
		private Map<Long, Report> reports = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			int id = resultSet.getInt(index++);
			String periodOfTime = resultSet.getString(index++);
			double income = resultSet.getDouble(index++);
			
			Report report = reports.get(id);
			if (report == null) {
				report = new Report(id,periodOfTime,income);
				reports.put((long) report.getId(), report);
				
			}	
	    }
		public List<Report> getReports() {
			return new ArrayList<>(reports.values());
		}	
	}
	@Override
	public Report findOne(int id) {
		String sql =
				"SELECT reportId,periodOfTime,income FROM Report WHERE ReportId = ?";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getReports().get(0);
	}
	
	@Override
	public List<Report> findAll() {
		String sql = "SELECT reportId,periodOfTime,income FROM Report ORDER BY reportId";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getReports();
		
	}
	
	@Transactional
	@Override
	public int save(Report report) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO Report (periodOfTime,income) VALUES (?, ?)";
				 
				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, report.getPeriodOfTime());
				preparedStatement.setDouble(index++, report.getIncome());
			

				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		
		
		 if (uspeh) {
			 long reportId = keyHolder.getKey().longValue();
		        
		        String sql = "INSERT INTO reportbestRatedTrainer (trainerId, reportId) VALUES (?, ?)";
		        
		        for (Trainer trainer : report.getBestRankedTrainers()) {
		        	uspeh = jdbcTemplate.update(sql, trainer.getId(), reportId) == 1;
		            if (!uspeh) {
		                break;
		            }
		        }
		        
		        String sql1 = "INSERT INTO reportMostPaidTrainer (trainerId, reportId) VALUES (?, ?)";

		        
		        for (Trainer trainer : report.getHighestPaidTrainers()) {
		        	uspeh = jdbcTemplate.update(sql1, trainer.getId(), reportId) == 1;
		            if (!uspeh) {
		                break;
		            }
		        }
		    }
		
		
		return uspeh?1:0;
	}
	
@Transactional
@Override
public int update(Report report) {
	
	String sql = "UPDATE Report SET periodOfTime = ?, income = ? WHERE reportId = ?";
	boolean uspeh = jdbcTemplate.update(sql,report.getPeriodOfTime(),report.getIncome()) == 1;
	
	String deleteSql = "DELETE FROM reportbestRatedTrainer WHERE reportId = ?";
	jdbcTemplate.update(deleteSql, report.getId());
	
	String insertSql = "INSERT INTO reportbestRatedTrainer (trainerId, reportId) values (?,?)";
	
	   for (Trainer trainer : report.getBestRankedTrainers()) {
       	uspeh = jdbcTemplate.update(insertSql, trainer.getId(), report.getId()) == 1;
           if (!uspeh) {
               break;
           }
       }
	   
	 String deleteSql1 = "DELETE FROM reportMostPaidTrainer WHERE reportId = ?";  
     jdbcTemplate.update(deleteSql1, report.getId());

	   
   String insertSql1 = "INSERT INTO reportMostPaidTrainer (trainerId,reportId) values (?,?)";
   
   for (Trainer trainer : report.getHighestPaidTrainers()) {
   	uspeh = jdbcTemplate.update(insertSql1, trainer.getId(), report.getId()) == 1;
       if (!uspeh) {
           break;
       }
   }
   return uspeh?1:0;
	
}

@Transactional
@Override
public int delete(int id) {
	String sql = "DELETE FROM Report WHERE reportId = ?";
	String sql1 = "DELETE FROM reportbestRatedTrainer WHERE reportId = ?";
	String sql2 = "DELETE FROM reportMostPaidTrainer WHERE reportId = ?";

	jdbcTemplate.update(sql1, id);
	jdbcTemplate.update(sql2,id);
	return jdbcTemplate.update(sql, id);
}


@Transactional
@Override
public List<Trainer> getBestRankedTrainers(int id) {
    String sql = "SELECT trainerId FROM reportbestRatedTrainer WHERE reportId = ?";
    List<Integer> trainerIds = jdbcTemplate.queryForList(sql, new Object[]{id}, Integer.class);

    List<Trainer> trainers = new ArrayList<>();
    for (int trainerId : trainerIds) {
        sql = "SELECT * FROM Trainer WHERE id = ?";
        trainers.add(jdbcTemplate.queryForObject(sql, new Object[]{trainerId}, new BeanPropertyRowMapper<>(Trainer.class)));
    }

    return trainers;
}

@Transactional
@Override
public List<Trainer> getMostPaidTrainers(int id) {
    String sql = "SELECT trainerId FROM reportMostPaidTrainer WHERE reportId = ?";
    List<Integer> trainerIds = jdbcTemplate.queryForList(sql, new Object[]{id}, Integer.class);

    List<Trainer> trainers = new ArrayList<>();
    for (int trainerId : trainerIds) {
        sql = "SELECT * FROM Trainer WHERE id = ?";
        trainers.add(jdbcTemplate.queryForObject(sql, new Object[]{trainerId}, new BeanPropertyRowMapper<>(Trainer.class)));
    }

    return trainers;
}





}
