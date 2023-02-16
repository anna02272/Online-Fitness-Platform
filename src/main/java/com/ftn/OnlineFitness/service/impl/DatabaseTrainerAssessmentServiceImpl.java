package com.ftn.OnlineFitness.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.OnlineFitness.dao.TrainerAssessmentDAO;
import com.ftn.OnlineFitness.model.TrainerAssessment;
import com.ftn.OnlineFitness.service.TrainerAssessmentService;


@Service
public class DatabaseTrainerAssessmentServiceImpl implements TrainerAssessmentService{

	@Autowired
	private TrainerAssessmentDAO trainerAssessmentDAO;



	@Override
	public TrainerAssessment save(TrainerAssessment trainerAssessment) {
		trainerAssessmentDAO.save(trainerAssessment);
		return trainerAssessment;
	}


}

