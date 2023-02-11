package com.ftn.OnlineFitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OnlineFitnessApplication extends SpringBootServletInitializer {
	  
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OnlineFitnessApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineFitnessApplication.class, args);
	}
}


