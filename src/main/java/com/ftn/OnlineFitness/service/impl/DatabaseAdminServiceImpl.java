package com.ftn.OnlineFitness.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.OnlineFitness.dao.AdminDAO;
import com.ftn.OnlineFitness.model.Admin;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.service.AdminService;


@Service
public class DatabaseAdminServiceImpl implements AdminService{

	@Autowired
	private AdminDAO adminDAO;

	@Override
	public Admin findOne(int id) {
		return adminDAO.findOne(id);
	}

	@Override
	public List<Admin> findAll() {
		return adminDAO.findAll();
	}

	

	@Override
	public Admin update(Admin admin) {
		adminDAO.update(admin);
		return admin;
	}

	
	
	@Override
	public List<ELanguage> getAdminLanguages(int id) {
         return adminDAO.getAdminLanguages(id);		
	}

}

