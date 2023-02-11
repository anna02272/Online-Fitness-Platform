package com.ftn.OnlineFitness.dao;

import java.util.List;

import com.ftn.OnlineFitness.model.Appointment;


public interface AppointmentDAO {
	
	public Appointment findOne(int id);

	public List<Appointment> findAll();
	
	public int save(Appointment appointment);

	public int update(Appointment appointment);

	public int delete(int id);
}
