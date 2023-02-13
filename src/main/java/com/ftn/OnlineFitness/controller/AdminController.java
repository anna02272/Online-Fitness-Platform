package com.ftn.OnlineFitness.controller;

import java.io.IOException;
import java.util.List;

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

import com.ftn.OnlineFitness.model.Admin;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.service.AdminService;

@Controller
@RequestMapping(value= "/admins")
public class  AdminController implements ServletContextAware {

		
		@Autowired
		private ServletContext servletContext;
		private  String bURL; 
		
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
		public ModelAndView index() {
			List<Admin> admins = adminService.findAll();	
			
			for (Admin admin : admins) {
				admin.setAdditionalLanguages(adminService.getAdminLanguages(admin.getId()));
			}
			
			
			ModelAndView rezultat = new ModelAndView("admins"); 
			rezultat.addObject("admins", admins); 

			return rezultat; 
		}

		
		
		
	
		@SuppressWarnings("unused")
		@PostMapping(value="/edit")
		public void Edit(@RequestParam int id, @RequestParam(required = true) String name, @RequestParam(required = true) String surname,  
				@RequestParam(required = true) String email,@RequestParam(required = true) String password,
				@RequestParam(required = true) String phoneNumber, @RequestParam(required = true) String address,
				@RequestParam(required = true) String cardNumber, @RequestParam(required = true) ELanguage nativeLanguage,
				 @RequestParam(required = false) List<ELanguage> adminLanguages, 
				 HttpServletResponse response) throws IOException {	
			Admin admin = adminService.findOne(id);
			
			
			if(admin != null) {
				if(name != null && !name.trim().equals(""))
					admin.setName(name);
				if(surname != null && !surname.trim().equals(""))
					admin.setSurname(surname);
				if(email != null && !email.trim().equals(""))
					admin.setEmail(email);
				if(password != null && !password.trim().equals(""))
					admin.setPassword(password);
				if(phoneNumber != null && !phoneNumber.trim().equals(""))
					admin.setPhoneNumber(phoneNumber);
				if(address != null && !address.trim().equals(""))
					admin.setAddress(address);
				if(cardNumber != null && !cardNumber.trim().equals(""))
					admin.setCardNumber(cardNumber);
				if(nativeLanguage != null )
					admin.setNativeLanguage(nativeLanguage);
				if (adminLanguages != null && !adminLanguages.isEmpty()) {
					  admin.setAdditionalLanguages(adminLanguages);
					}
				

				
			
			}
			Admin saved = adminService.update(admin);
			response.sendRedirect(bURL+"admins");
		}
		
	
		
		@GetMapping(value="/details")
		@ResponseBody
		public ModelAndView details(@RequestParam int id) {	
			Admin admin = adminService.findOne(id);
			List<ELanguage> adminLanguages = adminService.getAdminLanguages(id);

			
		
			ModelAndView rezultat = new ModelAndView("admin"); 
			rezultat.addObject("admin", admin); 
			rezultat.addObject("adminLanguages", adminLanguages);

			return rezultat; 
		}

	}

