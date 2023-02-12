package com.ftn.OnlineFitness.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ftn.OnlineFitness.model.Admin;
import com.ftn.OnlineFitness.model.Trainer;
import com.ftn.OnlineFitness.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.OnlineFitness.service.ClientService;
import com.ftn.OnlineFitness.service.TrainerService;

@Controller 

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


	@GetMapping("/login")
	public String showLogin(){
		return "login";
	}
@PostMapping("/login")
	public void login(@RequestParam String email, @RequestParam String password, HttpServletResponse response, HttpSession session)throws IOException{
	Trainer trainer = trainerService.findByEmailAndPassword(email,password);
	Admin admin = adminService.findOneByEmailAndPassword(email,password);
	if (trainer!=null && trainer.isActive){
		session.setAttribute("trainer",trainer);
		session.setAttribute("role",trainer.getRole().toString());
		response.sendRedirect(bURL + "index");
	}else if (admin!= null){
		session.setAttribute("admin",admin);
		session.setAttribute("role",admin.getRole().toString());
		response.sendRedirect(bURL + "index");
	}
	else {
		response.sendRedirect(bURL + "login?error");
	}
}
@GetMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/login?logout";
}
	
	
	
	
	

}
