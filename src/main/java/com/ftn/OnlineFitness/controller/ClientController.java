package com.ftn.OnlineFitness.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.OnlineFitness.model.Client;
import com.ftn.OnlineFitness.model.EGoals;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.EProps;
import com.ftn.OnlineFitness.model.ERole;
import com.ftn.OnlineFitness.service.ClientService;

@Controller
@RequestMapping(value= "/clients")
public class ClientController implements ServletContextAware {

		
		@Autowired
		private ServletContext servletContext;
		private  String bURL; 
		
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
		public ModelAndView index() {
			List<Client> clients = clientService.findAll();	
			
			for (Client client : clients) {
				client.setAdditionalLanguages(clientService.getClientLanguages(client.getId()));
				client.setGoals(clientService.getClientGoals(client.getId()));
				client.setProps(clientService.getClientProps(client.getId()));
				
			}
			
			
			ModelAndView rezultat = new ModelAndView("clients"); 
			rezultat.addObject("clients", clients); 

			return rezultat; 
		}

		@GetMapping(value="/add")
		public String create(HttpSession session, HttpServletResponse response){
			return "addClient"; 
		}

		
		@SuppressWarnings("unused")
		@PostMapping(value="/add")
		public void create(@RequestParam(required = true) String name, @RequestParam(required = true) String surname,  
				@RequestParam(required = true) String email,@RequestParam(required = true) String password,
				@RequestParam(required = true) String phoneNumber, @RequestParam(required = true) String address,
				@RequestParam(required = true) String cardNumber, @RequestParam(required = true) ELanguage nativeLanguage,
				 @RequestParam(required = false) List<ELanguage> additionalLanguages, @RequestParam (required = true) ERole role,
				 @RequestParam(required = true) int height, @RequestParam(required = true) int weight,
				 @RequestParam(required = true) String illnessOrConditions,
				 @RequestParam(required = false) List<EGoals> goals,
				 @RequestParam(required = false) List<EProps> props,
				 @RequestParam(required = true) double waistCircumference,
				 @RequestParam(required = true) double stomachCircumference,
				HttpServletResponse response) throws IOException {		
			Client client = new Client( name, surname, email, password, phoneNumber, address, cardNumber, 
					nativeLanguage, additionalLanguages, role,
					height, weight, illnessOrConditions, goals, props, waistCircumference, stomachCircumference);
			Client saved = clientService.save(client);
			response.sendRedirect(bURL+"clients");
		}
		
	
		@SuppressWarnings("unused")
		@PostMapping(value="/edit")
		public void Edit(@RequestParam int id, @RequestParam(required = true) String name, @RequestParam(required = true) String surname,  
				@RequestParam(required = true) String email,@RequestParam(required = true) String password,
				@RequestParam(required = true) String phoneNumber, @RequestParam(required = true) String address,
				@RequestParam(required = true) String cardNumber, @RequestParam(required = true) ELanguage nativeLanguage,
				 @RequestParam(required = false) List<ELanguage> clientLanguages, @RequestParam (required = true) ERole role,
				 @RequestParam(required = true) int height, @RequestParam(required = true) int weight,
				 @RequestParam(required = true) String illnessOrConditions,
				 @RequestParam(required = false) List<EGoals> clientGoals,
				 @RequestParam(required = false) List<EProps> clientProps,
				 @RequestParam(required = true) double waistCircumference,
				 @RequestParam(required = true) double stomachCircumference, 
				 HttpServletResponse response) throws IOException {	
			Client client = clientService.findOne(id);
			
			
			if(client != null) {
				if(name != null && !name.trim().equals(""))
					client.setName(name);
				if(surname != null && !surname.trim().equals(""))
					client.setSurname(surname);
				if(email != null && !email.trim().equals(""))
					client.setEmail(email);
				if(password != null && !password.trim().equals(""))
					client.setPassword(password);
				if(phoneNumber != null && !phoneNumber.trim().equals(""))
					client.setPhoneNumber(phoneNumber);
				if(address != null && !address.trim().equals(""))
					client.setAddress(address);
				if(cardNumber != null && !cardNumber.trim().equals(""))
					client.setCardNumber(cardNumber);
				if(nativeLanguage != null )
					client.setNativeLanguage(nativeLanguage);
				if (clientLanguages != null && !clientLanguages.isEmpty()) {
					  client.setAdditionalLanguages(clientLanguages);
					}
				if(role != null )
					client.setRole(role);
				if (height >= 0) {
					  client.setHeight(height);
				}
				if (weight >= 0) {
					  client.setWeight(weight);
				}
				if(illnessOrConditions != null && !illnessOrConditions.trim().equals(""))
					client.setIllnessOrConditions(illnessOrConditions);
				if (clientGoals != null && !clientGoals.isEmpty()) {
					  client.setGoals(clientGoals);
					}
				if (clientProps != null && !clientProps.isEmpty()) {
					  client.setProps(clientProps);
					}
				if (waistCircumference >= 0) {
					  client.setWaistCircumference(waistCircumference);
				}
				if (stomachCircumference >= 0) {
					  client.setStomachCircumference(stomachCircumference);
				}
				
			
			}
			Client saved = clientService.update(client);
			response.sendRedirect(bURL+"clients");
		}
		
	
		@SuppressWarnings("unused")
		@PostMapping(value="/delete")
		public void delete(@RequestParam int id, HttpServletResponse response) throws IOException {		
			Client deleted = clientService.delete(id);
			response.sendRedirect(bURL+"clients");
		}
		
		@GetMapping(value="/details")
		@ResponseBody
		public ModelAndView details(@RequestParam int id) {	
			Client client = clientService.findOne(id);
			List<ELanguage> clientLanguages = clientService.getClientLanguages(id);
			List<EGoals> clientGoals = clientService.getClientGoals(id);
			List<EProps> clientProps = clientService.getClientProps(id);

			
		
			ModelAndView rezultat = new ModelAndView("client"); 
			rezultat.addObject("client", client); 
			rezultat.addObject("clientLanguages", clientLanguages);
			rezultat.addObject("clientGoals", clientGoals);
			rezultat.addObject("clientProps", clientProps);

			return rezultat; 
		}

	}

