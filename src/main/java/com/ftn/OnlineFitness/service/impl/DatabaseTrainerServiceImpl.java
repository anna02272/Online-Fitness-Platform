package com.ftn.OnlineFitness.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.OnlineFitness.dao.TrainerDAO;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.Trainer;
import com.ftn.OnlineFitness.service.TrainerService;


@Service
public class DatabaseTrainerServiceImpl implements TrainerService{

	@Autowired
	private TrainerDAO trainerDAO;

	@Override
	public Trainer findOne(int id) {
		return trainerDAO.findOne(id);
	}

	@Override
	public List<Trainer> findAll() {
		return trainerDAO.findAll();
	}

	@Override
	public Trainer save(Trainer trainer) {
		trainerDAO.save(trainer);
		return trainer;
	}

	@Override
	public Trainer update(Trainer trainer) {
		trainerDAO.update(trainer);
		return trainer;
	}

	@Override
	public Trainer delete(int id) {
		Trainer trainer = trainerDAO.findOne(id);
		if(trainer != null) {
			trainerDAO.delete(id);
		}
		return trainer;
	}
	
	@Override
	public List<ELanguage> getTrainerLanguages(int id) {
         return trainerDAO.getTrainerLanguages(id);		
	}
	
	@Override
	public Trainer findByEmail(String email) {
		return trainerDAO.findByEmail(email);
	}
	
	@Override
	public Trainer findByPhoneNumber(String phoneNumber) {
		return trainerDAO.findByPhoneNumber(phoneNumber);
	}
	
	@Override
	public Trainer findByCardNumber(String cardNumber) {
		return trainerDAO.findByCardNumber(cardNumber);
	}


}

