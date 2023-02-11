package com.ftn.OnlineFitness.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.OnlineFitness.dao.AppointmentDAO;
import com.ftn.OnlineFitness.model.Appointment;
import com.ftn.OnlineFitness.service.AppointmentService;

@Service
public class DatabaseAppointmentServiceImpl implements AppointmentService{

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Override
	public Appointment findOne(int id) {
		return appointmentDAO.findOne(id);
	}

	@Override
	public List<Appointment> findAll() {
		return appointmentDAO.findAll();
	}

	@Override
	public Appointment save(Appointment appointment) {
		appointmentDAO.save(appointment);
		return appointment;
	}

	@Override
	public Appointment update(Appointment appointment) {
		appointmentDAO.update(appointment);
		return appointment;
	}

	@Override
	public Appointment delete(int id) {
		Appointment appointment = appointmentDAO.findOne(id);
		if(appointment != null) {
			appointmentDAO.delete(id);
		}
		return appointment;
	}

}

