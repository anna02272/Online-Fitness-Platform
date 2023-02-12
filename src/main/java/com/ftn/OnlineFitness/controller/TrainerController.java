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

import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.ERole;
import com.ftn.OnlineFitness.model.Trainer;
import com.ftn.OnlineFitness.service.TrainerService;

@Controller
@RequestMapping(value= "/trainers")
public class TrainerController implements ServletContextAware {

		
		@Autowired
		private ServletContext servletContext;
		private  String bURL; 
		
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
			List<Trainer> trainers = trainerService.findAll();	
			
			for (Trainer trainer : trainers) {
				trainer.setAdditionalLanguages(trainerService.getTrainerLanguages(trainer.getId()));
			}
			
			
			ModelAndView rezultat = new ModelAndView("trainers"); 
			rezultat.addObject("trainers", trainers); 

			return rezultat; 
		}

		@GetMapping(value="/add")
		public String create(HttpSession session, HttpServletResponse response){
			return "addTrainer"; 
		}

		
		@SuppressWarnings("unused")
		@PostMapping(value="/add")
		public void create(@RequestParam(required = true) String name, @RequestParam(required = true) String surname,  
				@RequestParam(required = true) String email,@RequestParam(required = true) String password,
				@RequestParam(required = true) String phoneNumber, @RequestParam(required = true) String address,
				@RequestParam(required = true) String cardNumber, @RequestParam(required = true) ELanguage nativeLanguage,
				 @RequestParam(required = false) List<ELanguage> additionalLanguages, @RequestParam (required = true) ERole role,
				 @RequestParam(required = true) String certificate, @RequestParam(required = true) String diploma,
				 @RequestParam(required = true) String title,
				 @RequestParam(required = true) double salary,
				HttpServletResponse response) throws IOException {		
			Trainer trainer = new Trainer( name, surname, email, password, phoneNumber, address, cardNumber, 
					nativeLanguage, additionalLanguages, role,
					certificate, diploma, title, false, salary);
			Trainer saved = trainerService.save(trainer);
			response.sendRedirect(bURL+"trainers");
		}
		
	
		@SuppressWarnings("unused")
		@PostMapping(value="/edit")
		public void Edit(@RequestParam int id, @RequestParam(required = true) String name, @RequestParam(required = true) String surname,  
				@RequestParam(required = true) String email,@RequestParam(required = true) String password,
				@RequestParam(required = true) String phoneNumber, @RequestParam(required = true) String address,
				@RequestParam(required = true) String cardNumber, @RequestParam(required = true) ELanguage nativeLanguage,
				 @RequestParam(required = false) List<ELanguage> trainerLanguages, @RequestParam (required = true) ERole role,
				 @RequestParam(required = true) String certificate, @RequestParam(required = true) String diploma,
				 @RequestParam(required = true) String title, @RequestParam(required = false, defaultValue="false") boolean isActive,
				 @RequestParam(required = true) double salary, HttpServletResponse response) throws IOException {	
			Trainer trainer = trainerService.findOne(id);
			
			
			if(trainer != null) {
				if(name != null && !name.trim().equals(""))
					trainer.setName(name);
				if(surname != null && !surname.trim().equals(""))
					trainer.setSurname(surname);
				if(email != null && !email.trim().equals(""))
					trainer.setEmail(email);
				if(password != null && !password.trim().equals(""))
					trainer.setPassword(password);
				if(phoneNumber != null && !phoneNumber.trim().equals(""))
					trainer.setPhoneNumber(phoneNumber);
				if(address != null && !address.trim().equals(""))
					trainer.setAddress(address);
				if(cardNumber != null && !cardNumber.trim().equals(""))
					trainer.setCardNumber(cardNumber);
				if(nativeLanguage != null )
					trainer.setNativeLanguage(nativeLanguage);
				if (trainerLanguages != null && !trainerLanguages.isEmpty()) {
					  trainer.setAdditionalLanguages(trainerLanguages);
					}
				if(role != null )
					trainer.setRole(role);
				if(certificate != null && !certificate.trim().equals(""))
					trainer.setCertificate(certificate);
				if(diploma != null && !diploma.trim().equals(""))
					trainer.setDiploma(diploma);
				if(title != null && !title.trim().equals(""))
					trainer.setTitle(title);
				trainer.setActive(isActive);

				if (salary >= 0) {
					  trainer.setSalary(salary);
				}

				
			
			}
			Trainer saved = trainerService.update(trainer);
			response.sendRedirect(bURL+"trainers");
		}
		
	
		@SuppressWarnings("unused")
		@PostMapping(value="/delete")
		public void delete(@RequestParam int id, HttpServletResponse response) throws IOException {		
			Trainer deleted = trainerService.delete(id);
			response.sendRedirect(bURL+"trainers");
		}
		
		@GetMapping(value="/details")
		@ResponseBody
		public ModelAndView details(@RequestParam int id) {	
			Trainer trainer = trainerService.findOne(id);
			List<ELanguage> trainerLanguages = trainerService.getTrainerLanguages(id);
			System.out.println("trainerLanguages: " + trainerLanguages);

			
		
			ModelAndView rezultat = new ModelAndView("trainer"); 
			rezultat.addObject("trainer", trainer); 
			rezultat.addObject("trainerLanguages", trainerLanguages);

			return rezultat; 
		}

	}
