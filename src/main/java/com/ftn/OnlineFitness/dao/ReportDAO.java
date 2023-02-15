package com.ftn.OnlineFitness.dao;

import java.util.List;

import com.ftn.OnlineFitness.model.Report;
import com.ftn.OnlineFitness.model.Trainer;

public interface ReportDAO {
	
	public Report findOne(int id);

	public List<Report> findAll();
	
	public int save(Report report);

	public int update(Report report);

	public int delete(int id);
	
	public List<Trainer> getBestRankedTrainers(int id);
	
	public List<Trainer> getMostPaidTrainers(int id);

}
