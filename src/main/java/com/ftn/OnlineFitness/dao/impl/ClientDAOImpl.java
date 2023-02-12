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
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.OnlineFitness.dao.ClientDAO;
import com.ftn.OnlineFitness.model.Admin;
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
			
			ERole role = ERole.valueOf(resultSet.getString(index++));
			
			int height = resultSet.getInt(index++);
			int weight = resultSet.getInt(index++);
			String illnessOrConditions = resultSet.getString(index++);
			
			double waistCircumference = resultSet.getDouble(index++);
			double stomachCircumference = resultSet.getDouble(index++);
			


			Client client = clients.get(id);
			if (client == null) {
				client = new Client(id, name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, role,
						height, weight, illnessOrConditions,  waistCircumference, stomachCircumference);
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
				String sql =  "INSERT INTO ClientTable (name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, role,"
				 		+ " height, weight, illnessOrCondition,waistCircumference ,stomachCircumference) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
		 if (uspeh) {
		        long clientId = keyHolder.getKey().longValue();
		        if (client.getAdditionalLanguages() != null) {
		        String sql = "INSERT INTO clientSpeaks (clientId, speaksLangague) VALUES (?, ?)";
		        for (ELanguage additionalLanguage : client.getAdditionalLanguages()) {
		            jdbcTemplate.update(sql, new Object[]{clientId, additionalLanguage.name()});
		        }
		       }
		        if (client.getProps() != null) {
		        String sql1 = "INSERT INTO clientGoals(clientId, goal) VALUES (?, ?)";
		        for (EGoals goal : client.getGoals()) {
		            jdbcTemplate.update(sql1, new Object[]{clientId, goal.name()});
		        }
		        }
		        if (client.getGoals() != null) {
		        String sql2 = "INSERT INTO clientProps (clientId, prop) VALUES (?, ?)";
		        for (EProps prop : client.getProps()) {
		            jdbcTemplate.update(sql2, new Object[]{clientId, prop.name()});
		        }
		        }
		    }
		
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int update(Client client) {	
		
		String sql =  "UPDATE ClientTable SET name = ?, surname = ?, email = ?, password = ?,"
				+ " phoneNumber = ?, address = ?, cardNumber = ?, nativeLanguage = ?, "
				+ "height = ?, weight = ?, illnessOrCondition = ?, waistCircumference = ?, "
				+ "stomachCircumference = ? WHERE id = ?";
		boolean uspeh = jdbcTemplate.update(sql, client.getName(), client.getSurname(),  client.getEmail(),
				client.getPassword(), client.getPhoneNumber(), client.getAddress(), client.getCardNumber(), 
				client.getNativeLanguage().name(),client.getHeight(), client.getWeight(), client.getIllnessOrConditions(), 
				client.getWaistCircumference(), client.getStomachCircumference(), client.getId()) == 1;
		
		String deleteSql = "DELETE FROM clientSpeaks WHERE clientId = ?";
		jdbcTemplate.update(deleteSql, client.getId());
		
		String insertSql = "INSERT INTO clientSpeaks (clientId, speaksLangague) values (?,?)";
		for (ELanguage language : client.getAdditionalLanguages()) {
			jdbcTemplate.update(insertSql, client.getId(), language.name());
		}
		String deleteSql1 = "DELETE FROM clientGoals WHERE clientId = ?";
		jdbcTemplate.update(deleteSql1, client.getId());
		
		String insertSql1 = "INSERT INTO clientGoals (clientId, goal) values (?,?)";
		for (EGoals goal : client.getGoals()) {
			jdbcTemplate.update(insertSql1, client.getId(), goal.name());
		}
		String deleteSql2 = "DELETE FROM clientProps WHERE clientId = ?";
		jdbcTemplate.update(deleteSql2, client.getId());
		
		String insertSql2 = "INSERT INTO clientProps (clientId, prop) values (?,?)";
		for (EProps prop : client.getProps()) {
			jdbcTemplate.update(insertSql2, client.getId(), prop.name());
		}
		
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int delete(int id) {
		String sql = "DELETE FROM ClientTable WHERE id = ?";
		String sql1 = "DELETE FROM ClientSpeaks WHERE clientId = ?";
		jdbcTemplate.update(sql1, id);
		return jdbcTemplate.update(sql, id);
	}
	
	@Transactional
	@Override
	public List<ELanguage> getClientLanguages(int id) {
		String sql = "SELECT speaksLangague FROM clientSpeaks WHERE clientId = ?";
		List<String> languageStrings = jdbcTemplate.queryForList(sql, new Object[] { id }, String.class);
		List<ELanguage> languages = languageStrings.stream().map(ELanguage::valueOf).collect(Collectors.toList());
		return languages;
	}
	
	@Transactional
	@Override
	public List<EGoals> getClientGoals(int id) {
		String sql = "SELECT goal FROM clientGoals WHERE clientId = ?";
		List<String> goalStrings = jdbcTemplate.queryForList(sql, new Object[] { id }, String.class);
		List<EGoals> goals = goalStrings.stream().map(EGoals::valueOf).collect(Collectors.toList());
		return goals;
	}
	
	@Transactional
	@Override
	public List<EProps> getClientProps(int id) {
		String sql = "SELECT prop FROM clientProps WHERE clientId = ?";
		List<String> propStrings = jdbcTemplate.queryForList(sql, new Object[] { id }, String.class);
		List<EProps> props = propStrings.stream().map(EProps::valueOf).collect(Collectors.toList());
		return props;
	}
	
	
	@Override
	public Client findByEmail(String email) {
		 String sql = "SELECT * FROM ClientTable c " +
                 "WHERE c.email = ?" + 
                 "ORDER BY c.id";
	   RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
	   jdbcTemplate.query(sql, rowCallbackHandler, email);
	   if (rowCallbackHandler.getClients().isEmpty()) {
		   return null;
	   }
	   return rowCallbackHandler.getClients().get(0);
   
   
	}
	
	@Override
	public Client findByPhoneNumber(String phoneNumber) {
		 String sql = "SELECT * FROM ClientTable c " +
                 "WHERE c.phoneNumber = ?" + 
                 "ORDER BY c.id";
	   RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
	   jdbcTemplate.query(sql, rowCallbackHandler, phoneNumber);
	   if (rowCallbackHandler.getClients().isEmpty()) {
		   return null;
	   }
	   return rowCallbackHandler.getClients().get(0);
   
   
	}
	
	
	@Override
	public Client findByCardNumber(String cardNumber) {
		 String sql = "SELECT * FROM ClientTable c " +
                 "WHERE c.cardNumber = ?" + 
                 "ORDER BY c.id";
	   RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
	   jdbcTemplate.query(sql, rowCallbackHandler, cardNumber);
	   if (rowCallbackHandler.getClients().isEmpty()) {
		   return null;
	   }
	   return rowCallbackHandler.getClients().get(0);
   
   
	}
	
	@Override
	public Client getByEmailAndPassword(String email, String lozinka) {
		 String sql = "SELECT * FROM ClientTable c " +
                 "WHERE c.email = ?  AND c.password = ?" + 
                 "ORDER BY c.id";
   
	

   RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
   jdbcTemplate.query(sql, rowCallbackHandler, email, lozinka);
   if (rowCallbackHandler.getClients().isEmpty()) {
	   return null;
   }
   return rowCallbackHandler.getClients().get(0);
   
	}
	
	
	
}
