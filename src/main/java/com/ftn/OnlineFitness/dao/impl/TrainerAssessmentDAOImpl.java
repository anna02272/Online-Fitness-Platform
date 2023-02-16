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

import com.ftn.OnlineFitness.dao.AppointmentDAO;
import com.ftn.OnlineFitness.dao.ClientDAO;
import com.ftn.OnlineFitness.dao.TrainerAssessmentDAO;
import com.ftn.OnlineFitness.dao.TrainerDAO;
import com.ftn.OnlineFitness.model.Appointment;
import com.ftn.OnlineFitness.model.Client;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.ERating;
import com.ftn.OnlineFitness.model.Trainer;
import com.ftn.OnlineFitness.model.TrainerAssessment;




@Repository
public class TrainerAssessmentDAOImpl implements TrainerAssessmentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TrainerDAO trainerDAO;
	
	@Autowired
	private ClientDAO clientDAO;
	
	 
	@Transactional
	@Override
	public int save(TrainerAssessment trainerAssessment) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO TrainerAssessment (idTrainer,idClient, rating, comment ) VALUES (?,1, ?, ? )";	
				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setInt(index++, trainerAssessment.getTrainer().getId());
//				preparedStatement.setInt(index++, trainerAssessment.getClient().getId());
				ERating rating = trainerAssessment.getRating() ;
				preparedStatement.setInt(index++, rating.getValue());
				preparedStatement.setString(index++, trainerAssessment.comment); 
				
				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		return uspeh?1:0;
	}
	
 
}
