package com.ftn.OnlineFitness.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.OnlineFitness.model.Appointment;
import com.ftn.OnlineFitness.model.Client;
import com.ftn.OnlineFitness.model.EGoals;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.ERating;
import com.ftn.OnlineFitness.model.Trainer;
import com.ftn.OnlineFitness.model.TrainerAssessment;
import com.ftn.OnlineFitness.service.AppointmentService;
import com.ftn.OnlineFitness.service.ClientService;
import com.ftn.OnlineFitness.service.TrainerAssessmentService;
import com.ftn.OnlineFitness.service.TrainerService;

@Controller
@RequestMapping(value="/trainerAssessment")
public class TrainerAssessmentController implements ServletContextAware {

	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
	@Autowired
	private TrainerAssessmentService trainerAssessmentService;
	
	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private ClientService clientService;
	

	@PostConstruct
	public void init() {	
		bURL = servletContext.getContextPath()+"/";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 
	

	
//	@GetMapping
//	public ModelAndView index() {
//		List<TrainerAssessment> trainerAssessment = appointmentService.findAll();		
//		
//		List<Trainer> trainers = trainerService.findAll();
//		List<Client> clients = clientService.findAll();
//
//		
//		ModelAndView rezultat = new ModelAndView("appointments"); 
//		
//		rezultat.addObject("appointments", appointments); 
//		rezultat.addObject("trainers", trainers);
//		rezultat.addObject("clients", clients);
//		
//
//		return rezultat; 
//	}

	
	@GetMapping(value="/add")
	public String create(HttpSession session, HttpServletResponse response ) {
		
		List<Trainer> trainers = trainerService.findAll();
		session.setAttribute("trainers", trainers);
		List<Client> clients = clientService.findAll();
		session.setAttribute("clients", clients);
		

		return "addTrainerAssessment"; 
	}
	
	@SuppressWarnings("unused")
	@PostMapping(value="/add")
	public void create(@RequestParam(value = "trainerId") @PathVariable("id") int trainerId,
//			@RequestParam(value = "clientId") @PathVariable("id") int clientId,
			@RequestParam(required = true) ERating rating,
			 @RequestParam(required = true) String comment,
			HttpServletResponse response) throws IOException {	
	  
	     Trainer trainer = trainerService.findOne(trainerId); 
//	     Client client = clientService.findOne(clientId);
	    
	     TrainerAssessment trainerAssessment = new TrainerAssessment(trainer, rating, comment );
	     TrainerAssessment saved = trainerAssessmentService.save(trainerAssessment);
	    response.sendRedirect(bURL+"appointments");
	}

	
	 
//	@GetMapping(value="/details")
//	@ResponseBody
//	public ModelAndView details(@RequestParam int id) {	
//		
//		Appointment appointment  = appointmentService.findOne(id);
//		List<Trainer> trainers = trainerService.findAll();
//		List<Client> clients = clientService.findAll();
//		
//		ModelAndView rezultat = new ModelAndView("appointment"); 
//		rezultat.addObject("appointment", appointment); 
//		rezultat.addObject("trainers", trainers); 
//		rezultat.addObject("clients", clients); 
//
//		return rezultat; 
//	}
//	@PostMapping(value="/cancel")
//	@ResponseBody
//	public ModelAndView cancel(@RequestParam int id) {
//		ModelAndView appointments = new ModelAndView("appointments");
//		Appointment appointment = appointmentService.findOne(id);
//		appointment.setFree(false);
//		appointment.setClient(null);
//		appointmentService.update(appointment);
//		
//		List<Appointment> allAppointments = appointmentService.findAll();
//		appointments.addObject("appointments", allAppointments);
//		return appointments;
//		
//		
//	}


}
