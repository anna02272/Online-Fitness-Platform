package com.ftn.OnlineFitness.service;

import java.util.List;

import com.ftn.OnlineFitness.model.Admin;
import com.ftn.OnlineFitness.model.ELanguage;

public interface AdminService {
	Admin findOne(int id); 
	List<Admin> findAll(); 
	Admin update(Admin admin); 
	List<ELanguage> getAdminLanguages(int id);
	Admin getByEmail(String email);
	Admin getByPhoneNumber(String phoneNumber);
	Admin getByCardNumber(String cardNumber);


}
