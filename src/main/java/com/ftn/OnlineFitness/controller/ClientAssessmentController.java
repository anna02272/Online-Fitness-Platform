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
import com.ftn.OnlineFitness.model.ClientAssessment;
import com.ftn.OnlineFitness.model.EGoals;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.ERating;
import com.ftn.OnlineFitness.model.Trainer;
import com.ftn.OnlineFitness.model.TrainerAssessment;
import com.ftn.OnlineFitness.service.AppointmentService;
import com.ftn.OnlineFitness.service.ClientAssessmentService;
import com.ftn.OnlineFitness.service.ClientService;
import com.ftn.OnlineFitness.service.TrainerAssessmentService;
import com.ftn.OnlineFitness.service.TrainerService;

@Controller
@RequestMapping(value="/clientAssessment")
public class ClientAssessmentController implements ServletContextAware {

	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
	@Autowired
	private ClientAssessmentService clientAssessmentService;
	
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
	
	@GetMapping(value="/add")
	public String create(HttpSession session, HttpServletResponse response ) {
		
		List<Trainer> trainers = trainerService.findAll();
		session.setAttribute("trainers", trainers);
		List<Client> clients = clientService.findAll();
		session.setAttribute("clients", clients);
		

		return "addClientAssessment"; 
	}
	
	@SuppressWarnings("unused")
	@PostMapping(value="/add")
	public void create(
			@RequestParam(value = "clientId") @PathVariable("id") int clientId,
			@RequestParam(required = true) ERating rating,
			 @RequestParam(required = true) String comment,
			HttpServletResponse response) throws IOException {	
	  
	    
	     Client client = clientService.findOne(clientId);
	    
	     ClientAssessment clientAssessment = new ClientAssessment(client, rating, comment );
	     ClientAssessment saved = clientAssessmentService.save(clientAssessment);
	    response.sendRedirect(bURL+"appointments");
	}

	
	 


}
