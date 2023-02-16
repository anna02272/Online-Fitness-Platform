package com.ftn.OnlineFitness.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.OnlineFitness.model.Report;
import com.ftn.OnlineFitness.model.Trainer;
import com.ftn.OnlineFitness.service.ReportService;
import com.ftn.OnlineFitness.service.TrainerService;

@Controller
@RequestMapping(value= "/reports")
public class ReportController implements ServletContextAware {
	
	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private TrainerService trainerService;
	
	@PostConstruct
	public void init() {	
		bURL = servletContext.getContextPath()+"/";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 
	
	@GetMapping
	public ModelAndView index() {
		List<Report> reports = reportService.findAll();	
		List<Trainer> trainers = trainerService.findAll();
        
			
		for (Report report : reports) {
			report.setBestRankedTrainers(reportService.getBestRankedTrainers(report.getId()));
			report.setHighestPaidTrainers(reportService.getMostPaidTrainers(report.getId()));
		}   
		
		
		ModelAndView rezultat = new ModelAndView("reports"); 
		rezultat.addObject("reports", reports); 
		rezultat.addObject("trainers",trainers);

		return rezultat; 
	}
	
	@GetMapping(value="/add")
	public String create(HttpSession session, HttpServletResponse response){
		List<Trainer> trainers = trainerService.findAll();
        session.setAttribute("trainers", trainers);
		
		
		return "addReport"; 
	}
	
	@PostMapping(value="/add")
	public void create(@RequestParam String periodOfTime,
	                    @RequestParam List<Integer> bestRatedTrainers,
	                    @RequestParam double income,
	                    @RequestParam List<Integer> mostPaidTrainers,
	                    HttpServletResponse response) throws IOException {
	    
	    List<Trainer> bestRankedTrainers = new ArrayList<>();
	    for (int trainerId : bestRatedTrainers) {
	        Trainer trainer = trainerService.findOne(trainerId);
	        if (trainer != null) {
	            bestRankedTrainers.add(trainer);
	        }
	    }
	    
	    List<Trainer> mostPaidTrainersList = new ArrayList<>();
	    for (int trainerId : mostPaidTrainers) {
	        Trainer trainer = trainerService.findOne(trainerId);
	        if (trainer != null) {
	            mostPaidTrainersList.add(trainer);
	        }
	    }
	    
	    Report newReport = new Report(periodOfTime,income,bestRankedTrainers,mostPaidTrainersList);
	    
	    Report saved = reportService.save(newReport);
	    
	    response.sendRedirect(bURL+"reports");
	}

	
	@SuppressWarnings("unused")
	@PostMapping(value="/delete")
	public void delete(@RequestParam int id, HttpServletResponse response) throws IOException {		
		Report deleted = reportService.delete(id);
		response.sendRedirect(bURL+"reports");
	}
	
	
	

}
