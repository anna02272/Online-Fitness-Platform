package com.ftn.OnlineFitness.service;

import java.util.List;

import com.ftn.OnlineFitness.model.Client;
import com.ftn.OnlineFitness.model.EGoals;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.EProps;



public interface ClientService {
	
	Client findOne(int id); 
	List<Client> findAll(); 
	Client save(Client client); 
	Client update(Client client); 
	Client delete(int id); 
	List<ELanguage> getClientLanguages(int id);
	List<EGoals> getClientGoals(int id);
	List<EProps> getClientProps(int id);





}
