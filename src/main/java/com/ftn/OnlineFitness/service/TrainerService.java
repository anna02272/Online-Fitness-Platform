package com.ftn.OnlineFitness.service;

import java.util.List;

import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.Trainer;


public interface TrainerService {
	
	Trainer findOne(int id); 
	List<Trainer> findAll(); 
	Trainer save(Trainer trainer); 
	Trainer update(Trainer trainer); 
	Trainer delete(int id);
	List<ELanguage> getTrainerLanguages(int id);
	Trainer findByEmail(String email);
	Trainer findByCardNumber(String cardNumber);
	Trainer findByPhoneNumber(String phoneNumber);
	Trainer findByEmailAndPassword(String email,String password);



}
