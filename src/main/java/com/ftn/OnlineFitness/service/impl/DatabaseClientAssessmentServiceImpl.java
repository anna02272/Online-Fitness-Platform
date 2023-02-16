package com.ftn.OnlineFitness.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.OnlineFitness.dao.ClientAssessmentDAO;
import com.ftn.OnlineFitness.dao.TrainerAssessmentDAO;
import com.ftn.OnlineFitness.model.ClientAssessment;
import com.ftn.OnlineFitness.model.TrainerAssessment;
import com.ftn.OnlineFitness.service.ClientAssessmentService;
import com.ftn.OnlineFitness.service.TrainerAssessmentService;


@Service
public class DatabaseClientAssessmentServiceImpl implements ClientAssessmentService{

	@Autowired
	private ClientAssessmentDAO clientAssessmentDAO;



	@Override
	public ClientAssessment save(ClientAssessment clientAssessment) {
		clientAssessmentDAO.save(clientAssessment);
		return clientAssessment;
	}


}

