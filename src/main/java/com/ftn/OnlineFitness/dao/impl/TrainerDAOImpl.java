package com.ftn.OnlineFitness.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.OnlineFitness.dao.TrainerDAO;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.ERole;
import com.ftn.OnlineFitness.model.Trainer;



@Repository
public class TrainerDAOImpl implements TrainerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	private class RowCallBackHandler implements RowCallbackHandler {

		private Map<Long, Trainer> trainers = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			int id = resultSet.getInt(index++);
			String name = resultSet.getString(index++);
			String surname = resultSet.getString(index++);
			String email = resultSet.getString(index++);
			String password = resultSet.getString(index++);
			String phoneNumber = resultSet.getString(index++);
			String address = resultSet.getString(index++);
			String cardNumber = resultSet.getString(index++);
			ELanguage nativeLanguage = ELanguage.valueOf(resultSet.getString(index++));
			
		
			ERole role = ERole.valueOf(resultSet.getString(index++));
			String certificate = resultSet.getString(index++);
			String diploma = resultSet.getString(index++);
			String title = resultSet.getString(index++);
			boolean isActive = resultSet.getBoolean(index++);
			double salary = resultSet.getDouble(index++);
			


			Trainer trainer = trainers.get(id);
			if (trainer == null) {
				trainer = new Trainer(id, name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, role,
						certificate, diploma, title, isActive, salary);
				trainers.put( (long) trainer.getId(), trainer); 
			}
			
		
		}

		public List<Trainer> getTrainers() {
			return new ArrayList<>(trainers.values());
		}

	}

	@Override
	public Trainer findOne(int id) {
		String sql = 
				"SELECT * FROM Trainer  " + 
				"WHERE id = ? " + 
				"ORDER BY id";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getTrainers().get(0);
	}

	@Override
	public List<Trainer> findAll() {
		String sql = 
				"SELECT * FROM Trainer " + 
						"ORDER BY id";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getTrainers();
	}
	

	@Transactional
	@Override
	public int save(Trainer trainer) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO Trainer (name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, role, certificate, diploma, title, isActive, salary) VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				 
				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, trainer.getName());
				preparedStatement.setString(index++, trainer.getSurname());
				preparedStatement.setString(index++, trainer.getEmail());
				preparedStatement.setString(index++, trainer.getPassword());
				preparedStatement.setString(index++, trainer.getPhoneNumber());
				preparedStatement.setString(index++, trainer.getAddress());
				preparedStatement.setString(index++, trainer.getCardNumber());
				ELanguage nativeLanguage = trainer.getNativeLanguage();
				preparedStatement.setString(index++, nativeLanguage.name());
				ERole role = trainer.getRole();
				preparedStatement.setString(index++, role.name());

				preparedStatement.setString(index++, trainer.getCertificate());
				preparedStatement.setString(index++, trainer.getDiploma());
				preparedStatement.setString(index++, trainer.getTitle());
				preparedStatement.setBoolean(index++, trainer.isActive());
				preparedStatement.setDouble(index++, trainer.getSalary());
				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int update(Trainer trainer) {	
		
		String sql = "UPDATE Trainer SET name = ?, surname = ?, email = ?, password = ?, phoneNumber = ?,"
				+ " address = ?, cardNumber = ?, nativeLanguage = ?, certificate = ?, diploma = ?, title = ?,"
				+ " isActive = ?, salary = ? WHERE id = ?";
		boolean uspeh = jdbcTemplate.update(sql, trainer.getName(), trainer.getSurname(),  trainer.getEmail(),
				trainer.getPassword(), trainer.getPhoneNumber(), trainer.getAddress(), trainer.getCardNumber(), 
				trainer.getNativeLanguage(),trainer.getCertificate(), trainer.getDiploma(), trainer.getTitle(), 
				trainer.isActive(), trainer.getSalary(), trainer.getId()) == 1;
		
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int delete(int id) {
		String sql = "DELETE FROM Trainer WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
}
