package com.ftn.OnlineFitness.dao;

import java.util.List;

import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.Trainer;


public interface TrainerDAO {
	
	public Trainer findOne(int id);

	public List<Trainer> findAll();
	
	public int save(Trainer trainer);

	public int update(Trainer trainer);

	public int delete(int id);
	
	public List<ELanguage> getTrainerLanguages(int id);
}
