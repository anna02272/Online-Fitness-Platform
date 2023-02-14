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
import com.ftn.OnlineFitness.dao.TrainerDAO;
import com.ftn.OnlineFitness.model.Appointment;
import com.ftn.OnlineFitness.model.Client;
import com.ftn.OnlineFitness.model.ERating;
import com.ftn.OnlineFitness.model.Trainer;




@Repository
public class AppointmentDAOImpl implements AppointmentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TrainerDAO trainerDAO;
	
	@Autowired
	private ClientDAO clientDAO;
	
	
	private class RowCallBackHandler implements RowCallbackHandler {

		private Map<Long, Appointment> appointments = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			int id = resultSet.getInt(index++);
			int trainerId = resultSet.getInt(index++);
			Trainer trainer = trainerDAO.findOne(trainerId);
			
			int clientId = resultSet.getInt(index++);
			Client client = clientDAO.findOne(clientId);
			
			boolean isFree = resultSet.getBoolean(index++);
			LocalDateTime dateAndTime = resultSet.getObject(index++, LocalDateTime.class);
			

			float price = resultSet.getFloat(index++);
		

			Appointment appointment = appointments.get(id);
			if (appointment == null) {
				appointment = new Appointment(id,trainer, client, isFree, dateAndTime, price);
				appointments.put( (long) appointment.getId(), appointment); 
			}
			
		
		}

		public List<Appointment> getAppointments() {
			return new ArrayList<>(appointments.values());
		}

	}

	@Override
	public Appointment findOne(int id) {
		String sql = 
				"SELECT * FROM Appointment  " + 
				"WHERE id = ? " + 
				"ORDER BY id";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getAppointments().get(0);
	}

	@Override
	public List<Appointment> findAll() {
		String sql = 
				"SELECT * FROM Appointment " + 
						"ORDER BY id";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getAppointments();
	}
	

	@Transactional
	@Override
	public int save(Appointment appointment) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO Appointment (idTrainer, idClient, isFree, dateAndTime, price) VALUES (?, ?, ?, ?, ?)";	
				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setInt(index++, appointment.getTrainer().getId());
				preparedStatement.setInt(index++, appointment.getClient().getId());
				preparedStatement.setBoolean(index++, appointment.isFree());
				preparedStatement.setTimestamp(index++, Timestamp.valueOf(appointment.getDateAndTime()));
				preparedStatement.setFloat(index++, appointment.getPrice());
				
				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int update(Appointment appointment) {	
		
		String sql = "UPDATE Appointment SET idTrainer = ?, idClient = ?, isFree = ?, dateAndTime = ?, price = ?"
				+ " WHERE id = ?";
		boolean uspeh = jdbcTemplate.update(sql, appointment.getTrainer().getId(), appointment.getClient().getId(),  
				appointment.isFree(), appointment.getDateAndTime(), appointment.getPrice(), 
			 appointment.getId()) == 1;
		
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int delete(int id) {
		String sql = "DELETE FROM Appointment WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
}
