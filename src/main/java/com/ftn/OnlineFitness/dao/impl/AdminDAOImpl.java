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

import com.ftn.OnlineFitness.dao.AdminDAO;
import com.ftn.OnlineFitness.model.Admin;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.ERole;



@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	private class RowCallBackHandler implements RowCallbackHandler {

		private Map<Long, Admin> admins = new LinkedHashMap<>();
		
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
			


			Admin admin = admins.get(id);
			if (admin == null) {
				admin = new Admin(id, name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, additionalLanguages, role);
				admins.put( (long) admin.getId(), admin); 
			}
			
		
		}

		public List<Admin> getAdmins() {
			return new ArrayList<>(admins.values());
		}

	}

	@Override
	public Admin findOne(int id) {
		String sql = 
				"SELECT * FROM Admin  " + 
				"WHERE id = ? " + 
				"ORDER BY id";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getAdmins().get(0);
	}

	@Override
	public List<Admin> findAll() {
		String sql = 
				"SELECT * FROM Admin " + 
						"ORDER BY id";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getAdmins();
	}
	

	@Transactional
	@Override
	public int save(Admin admin) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO Admin (name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, role) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, admin.getName());
				preparedStatement.setString(index++, admin.getSurname());
				preparedStatement.setString(index++, admin.getEmail());
				preparedStatement.setString(index++, admin.getPassword());
				preparedStatement.setString(index++, admin.getPhoneNumber());
				preparedStatement.setString(index++, admin.getAddress());
				preparedStatement.setString(index++, admin.getCardNumber());
				ELanguage nativeLanguage = admin.getNativeLanguage();
				preparedStatement.setString(index++, nativeLanguage.name());
				ERole role = admin.getRole();
				preparedStatement.setString(index++, role.name());

				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int update(Admin admin) {	
		
		String sql = "UPDATE Admin SET name = ?, surname = ?, email = ?, password = ?, phoneNumber = ?, address = ?, cardNumber = ?, nativeLanguage = ? WHERE id = ?";
		boolean uspeh = jdbcTemplate.update(sql, admin.getName(), admin.getSurname(),  admin.getEmail(),admin.getPassword(), admin.getPhoneNumber(), admin.getAddress(), admin.getCardNumber(), admin.getNativeLanguage(), admin.getId()) == 1;
		
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int delete(int id) {
		String sql = "DELETE FROM admin WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
}
