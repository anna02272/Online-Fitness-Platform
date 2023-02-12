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

import com.ftn.OnlineFitness.dao.AdminDAO;
import com.ftn.OnlineFitness.model.Admin;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.ERole;
import com.ftn.OnlineFitness.model.Trainer;


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

			ERole role = ERole.valueOf(resultSet.getString(index++));
			


			Admin admin = admins.get(id);
			if (admin == null) {
				admin = new Admin(id, name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, role);
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
	public int update(Admin admin) {	
		
		String sql = "UPDATE Admin SET name = ?, surname = ?, email = ?, password = ?, phoneNumber = ?,"
				+ " address = ?, cardNumber = ?, nativeLanguage = ? WHERE id = ?";
		
		boolean uspeh = jdbcTemplate.update(sql, admin.getName(), admin.getSurname(),  admin.getEmail(),
				admin.getPassword(), admin.getPhoneNumber(), admin.getAddress(), admin.getCardNumber(), 
				admin.getNativeLanguage().name(), admin.getId()) == 1;
		
		String deleteSql = "DELETE FROM adminSpeaks WHERE adminId = ?";
		jdbcTemplate.update(deleteSql, admin.getId());
		
		String insertSql = "INSERT INTO adminSpeaks (adminId, speaksLangague) values (?,?)";
		for (ELanguage language : admin.getAdditionalLanguages()) {
			jdbcTemplate.update(insertSql, admin.getId(), language.name());
		}
		
		
		return uspeh?1:0;
	}
	
	
	@Transactional
	@Override
	public List<ELanguage> getAdminLanguages(int id) {
		String sql = "SELECT speaksLangague FROM adminSpeaks WHERE adminId = ?";
		List<String> languageStrings = jdbcTemplate.queryForList(sql, new Object[] { id }, String.class);
		List<ELanguage> languages = languageStrings.stream().map(ELanguage::valueOf).collect(Collectors.toList());
		return languages;
	}
	
	@Override
	public Admin getByEmail(String email) {
		String sql = 
				"SELECT * FROM Admin  " + 
				"WHERE email = ? " + 
				"ORDER BY id";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, email);
		 if (rowCallbackHandler.getAdmins().isEmpty()) {
			   return null;
		   }
		return rowCallbackHandler.getAdmins().get(0);
	}
	
	@Override
	public Admin getByPhoneNumber(String phoneNumber) {
		String sql = 
				"SELECT * FROM Admin  " + 
				"WHERE phoneNumber = ? " + 
				"ORDER BY id";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, phoneNumber);
		 if (rowCallbackHandler.getAdmins().isEmpty()) {
			   return null;
		   }
		return rowCallbackHandler.getAdmins().get(0);
	}
	
	@Override
	public Admin getByCardNumber(String cardNumber) {
		String sql = 
				"SELECT * FROM Admin a " + 
				"WHERE a.cardNumber = ?" + 
				"ORDER BY a.id";

		RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, cardNumber);
		 if (rowCallbackHandler.getAdmins().isEmpty()) {
			   return null;
		   }
		return rowCallbackHandler.getAdmins().get(0);
	}
	
	
	
	
	
	
	
	
	@Override
	public Admin getByEmailAndPassword(String email, String lozinka) {
		 String sql = "SELECT * FROM Admin a " +
                 "WHERE a.email = ?  AND a.password = ?" + 
                 "ORDER BY a.id";
   
	

   RowCallBackHandler rowCallbackHandler = new RowCallBackHandler();
   jdbcTemplate.query(sql, rowCallbackHandler, email, lozinka);
   if (rowCallbackHandler.getAdmins().isEmpty()) {
	   return null;
   }
   return rowCallbackHandler.getAdmins().get(0);
   
	}
	
	
	
	
	
	
}
