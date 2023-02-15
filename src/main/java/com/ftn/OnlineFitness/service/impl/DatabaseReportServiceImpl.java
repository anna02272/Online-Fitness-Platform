package com.ftn.OnlineFitness.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.OnlineFitness.dao.ReportDAO;
import com.ftn.OnlineFitness.model.Report;
import com.ftn.OnlineFitness.model.Trainer;
import com.ftn.OnlineFitness.service.ReportService;

@Service
public class DatabaseReportServiceImpl implements ReportService {
	

	@Autowired
	private ReportDAO reportDAO;

	@Override
	public Report findOne(int id) {
		return reportDAO.findOne(id);
	}

	@Override
	public List<Report> findAll() {
		return reportDAO.findAll();
	}

	@Override
	public Report save(Report report) {
		reportDAO.save(report);
		return report;
	}

	@Override
	public Report update(Report report) {
		reportDAO.update(report);
		return report;
	}

	@Override
	public Report delete(int id) {
		Report report = reportDAO.findOne(id);
		if(report != null) {
			reportDAO.delete(id);
		}
		return report;
	}
	
	@Override
	public List<Trainer> getBestRankedTrainers(int id) {
		return reportDAO.getBestRankedTrainers(id);
		
	}
	
	@Override
	public List<Trainer> getMostPaidTrainers(int id) {
		return reportDAO.getMostPaidTrainers(id);
		
	}
	

}
