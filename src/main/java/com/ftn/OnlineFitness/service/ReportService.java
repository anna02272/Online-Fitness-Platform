package com.ftn.OnlineFitness.service;

import java.util.List;

import com.ftn.OnlineFitness.model.Report;
import com.ftn.OnlineFitness.model.Trainer;
public interface ReportService {

	Report findOne(int id); 
	List<Report> findAll(); 
	Report save(Report report); 
	Report update(Report report); 
	Report delete(int id);
	List<Trainer> getBestRankedTrainers(int id);
	
	List<Trainer> getMostPaidTrainers(int id);

}
