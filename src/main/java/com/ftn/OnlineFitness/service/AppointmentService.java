package com.ftn.OnlineFitness.service;

import java.util.List;

import com.ftn.OnlineFitness.model.Appointment;


public interface AppointmentService {
	
	Appointment findOne(int id); 
	List<Appointment> findAll(); 
	Appointment save(Appointment appointment); 
	Appointment update(Appointment appointment); 
	Appointment delete(int id); 

}
