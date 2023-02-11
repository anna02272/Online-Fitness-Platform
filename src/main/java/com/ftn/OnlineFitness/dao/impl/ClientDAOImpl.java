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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.OnlineFitness.dao.ClientDAO;
import com.ftn.OnlineFitness.model.Client;
import com.ftn.OnlineFitness.model.EGoals;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.EProps;
import com.ftn.OnlineFitness.model.ERole;



@Repository
public class ClientDAOImpl implements ClientDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	private class RowCallBackHandler implements RowCallbackHandler {

		private Map<Long, Client> clients = new LinkedHashMap<>();
		
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
			List<ELanguage> additionalLanguages = new ArrayList<>();
			String languageString = resultSet.getString(index++);
			if (languageString != null) {
			  String[] languages = languageString.split(",");
			  for (String language : languages) {
			    additionalLanguages.add(ELanguage.valueOf(language));
			  }
			}

			ERole role = ERole.valueOf(resultSet.getString(index++));
			
			int height = resultSet.getInt(index++);
			int weight = resultSet.getInt(index++);
			String illnessOrConditions = resultSet.getString(index++);
			List<EGoals> allgoals = new ArrayList<>();
			String goalsString = resultSet.getString(index++);
			if (goalsString != null) {
			  String[] goals = goalsString.split(",");
			  for (String goal : goals) {
			   allgoals.add(EGoals.valueOf(goal));
			  }
			}
			List<EProps> allprops = new ArrayList<>();
			String propsString = resultSet.getString(index++);
			if (propsString != null) {
			  String[] props = propsString.split(",");
			  for (String prop : props) {
			   allprops.add(EProps.valueOf(prop));
			  }
			}
			double waistCircumference = resultSet.getDouble(index++);
			double stomachCircumference = resultSet.getDouble(index++);
			


			Client client = clients.get(id);
			if (client == null) {
				client = new Client(id, name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, additionalLanguages, role,
						height, weight, illnessOrConditions, allgoals, allprops, waistCircumference, stomachCircumference);
				clients.put( (long) client.getId(), client); 
			}
			
		
		}

		public List<Client> getClients() {
			return new ArrayList<>(clients.values());
		}

	}

	@Override
	public Client findOne(int id) {
		String sql = 
				"SELECT * FROM ClientTable  " + 
				"WHERE id = ? " + 
				"ORDER BY id";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getClients().get(0);
	}

	@Override
	public List<Client> findAll() {
		String sql = 
				"SELECT * FROM ClientTable " + 
						"ORDER BY id";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getClients();
	}
	

	@Transactional
	@Override
	public int save(Client client) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql =  "INSERT INTO ClientTable (name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, role"
				 		+ " height, weight, illnessOrCondition,waistCircumreference ,stomachCircumreference) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, client.getName());
				preparedStatement.setString(index++, client.getSurname());
				preparedStatement.setString(index++, client.getEmail());
				preparedStatement.setString(index++, client.getPassword());
				preparedStatement.setString(index++, client.getPhoneNumber());
				preparedStatement.setString(index++, client.getAddress());
				preparedStatement.setString(index++, client.getCardNumber());
				ELanguage nativeLanguage = client.getNativeLanguage();
				preparedStatement.setString(index++, nativeLanguage.name());
				ERole role = client.getRole();
				preparedStatement.setString(index++, role.name());

				preparedStatement.setInt(index++, client.getHeight());
				preparedStatement.setInt(index++, client.getWeight());
				preparedStatement.setString(index++, client.getIllnessOrConditions());
				preparedStatement.setDouble(index++, client.getWaistCircumference());
				preparedStatement.setDouble(index++, client.getStomachCircumference());
				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int update(Client client) {	
		
		String sql =  "UPDATE ClientTable SET name = ?, surname = ?, email = ?, password = ?,"
				+ " phoneNumber = ?, address = ?, cardNumber = ?, nativeLanguage = ?, "
				+ "height = ?, weight = ?, ilnessOrCondition = ?, waistCircumferefence = ?, "
				+ "stomachCircumference = ? WHERE id = ?";
		boolean uspeh = jdbcTemplate.update(sql, client.getName(), client.getSurname(),  client.getEmail(),
				client.getPassword(), client.getPhoneNumber(), client.getAddress(), client.getCardNumber(), 
				client.getNativeLanguage(),client.getHeight(), client.getWeight(), client.getIllnessOrConditions(), 
				client.getWaistCircumference(), client.getStomachCircumference(), client.getId()) == 1;
		
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int delete(int id) {
		String sql = "DELETE FROM ClientTable WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
}
