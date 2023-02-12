package com.ftn.OnlineFitness.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
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

import com.ftn.OnlineFitness.model.Client;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.ERole;
import com.ftn.OnlineFitness.model.Trainer;
import com.ftn.OnlineFitness.service.ClientService;
import com.ftn.OnlineFitness.service.TrainerService;

@Controller 
@RequestMapping(value="/registration")
public class RegistrationController implements ServletContextAware {

	@Autowired
	private ServletContext servletContext;
	private  String bURL;
	
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
	
	

	@GetMapping
	@ResponseBody
	public ModelAndView register(HttpServletResponse response) throws IOException {
		 List<ERole> filteredRoles = Arrays.stream(ERole.values())
		            .filter(role -> role != ERole.ADMIN)
		            .collect(Collectors.toList());
		
		
    	ModelAndView result = new ModelAndView("registration");
    	result.addObject("filteredRoles", filteredRoles);
    	
    	return result;

}
	
	@PostMapping
	public Object create(@RequestParam String name,@RequestParam String surname, @RequestParam String email,
			@RequestParam String password,@RequestParam String phoneNumber, @RequestParam String address,@RequestParam String cardNumber,@RequestParam ELanguage nativeLanguage,
			@RequestParam(required=false) List<ELanguage> additionalLanguages,ERole role) {
    	ModelAndView md = new ModelAndView("registration");
		boolean error = false;
		
		if (clientService.findByEmail(email) != null) {
			md.addObject("errorClientExistsEmail", true);
			error = true;
		}
		
		if (clientService.findByCardNumber(cardNumber) != null) {
			md.addObject("errorClientExistsCardNumber", true);
			error = true;

		}
		
		if (clientService.findByPhoneNumber(phoneNumber) != null) {
			md.addObject("errorClientExistsPhoneNumber", true);
			error = true;

		}
		
		if (trainerService.findByCardNumber(cardNumber) != null) {
			md.addObject("errorTrainerExistsCardNumber", true);
			error = true;
		}
		
		if (trainerService.findByPhoneNumber(phoneNumber) != null) {
			md.addObject("errorTrainerExistsPhoneNumber", true);
			error = true;
		}
		
		
		if (trainerService.findByEmail(email) != null) {
			md.addObject("errorTrainerExistsEmail", true);
			error=true;
		}
		
		
		if (!name.matches("[A-Z][a-zA-Z]*")) {
			md.addObject("errorName", true);
		    error = true;
		    
		}
		
		
		if (!surname.matches("[A-Z][a-zA-Z]*")) {
			md.addObject("errorSurname", true);
		    error = true;
		    
		}
		
		
		if (!email.matches("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
			md.addObject("errorEmail", true);
		    error = true;

		}
		
		

		if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$")) {
			md.addObject("errorPassword",true);
		    error = true;

		}
		
		if (!phoneNumber.matches("^\\d{10}$")) {
			md.addObject("errorPhone",true);
		    error = true;

		}
		
		if (error) {
			
			 List<ERole> filteredRoles = Arrays.stream(ERole.values())
			            .filter(role1 -> role != ERole.ADMIN)
			            .collect(Collectors.toList());
			 md.addObject("filteredRoles", filteredRoles);
			return md;
			
		}
    	
    	if (role.name().toString() == "CLIENT") {
    		Client client = new Client(name,surname,email,password,phoneNumber,address,cardNumber,
    				nativeLanguage,additionalLanguages,role,0,0,null,null,null,0,0);
    		Client saved = clientService.save(client);
    		return "redirect:/login";
     	}
    	
    	if (role.name().toString() == "TRAINER") {	
    		Trainer trainer = new Trainer(name,surname,email,password,
    				phoneNumber,address,cardNumber,
    				nativeLanguage,additionalLanguages,role,null,null,null,false,0);
    		Trainer saved = trainerService.save(trainer);
    		return "redirect:/login";
    	}
    	
    	
       return md;    	

		
		
		
	}
	
	

}
