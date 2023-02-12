package com.ftn.OnlineFitness.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

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
	
//	@Autowired
//	private AdminService adminService;
	
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
	
	
	
	
	

}
