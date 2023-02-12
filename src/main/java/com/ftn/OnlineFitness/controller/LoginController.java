package com.ftn.OnlineFitness.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.OnlineFitness.model.Admin;
import com.ftn.OnlineFitness.model.Client;
import com.ftn.OnlineFitness.model.Trainer;
import com.ftn.OnlineFitness.service.AdminService;
import com.ftn.OnlineFitness.service.ClientService;
import com.ftn.OnlineFitness.service.TrainerService;

@Controller 
@RequestMapping(value="/login")
public class LoginController implements ServletContextAware {
	
	@Autowired
	private ServletContext servletContext;
	private String bURL;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired 
	private TrainerService trainerService;
	
	@Autowired
	private AdminService adminService;
	
	@PostConstruct
	public void init() {
		bURL = servletContext.getContextPath()+"/";
	} 
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@GetMapping
	@ResponseBody
	public ModelAndView login(HttpServletResponse response) throws IOException {
    	ModelAndView result = new ModelAndView("login");
    	
    	return result;

	
}   
	
//IZMENJENO
@PostMapping
public Object login(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
	ModelAndView result = new ModelAndView("login");
	Admin admin = adminService.getByEmailAndPassword(email, password);
	if (admin != null) {
		request.getSession().setAttribute("user", admin);
		request.getSession().setAttribute("role",admin.getRole().toString());


		return "redirect:/";
	}
	
	Client client = clientService.getByEmailAndPassword(email, password);
	if (client != null) {
		request.getSession().setAttribute("user", client);
		request.getSession().setAttribute("role",client.getRole().toString());


		return "redirect:/";
	}
	
	Trainer trainer = trainerService.getByEmailAndPassword(email, password);
	if (trainer != null) {
		request.getSession().setAttribute("user", trainer);
		request.getSession().setAttribute("role",trainer.getRole().toString());


		return "redirect:/";
		
		
	}
	
	
	return result;


	
}
	
	
	
	
	

}
