package com.ftn.OnlineFitness.service;

import java.util.List;

import com.ftn.OnlineFitness.model.Client;



public interface ClientService {
	
	Client findOne(int id); 
	List<Client> findAll(); 
	Client save(Client client); 
	Client update(Client client); 
	Client delete(int id); 


}
