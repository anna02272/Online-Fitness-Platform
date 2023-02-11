package com.ftn.OnlineFitness.dao;
import java.util.List;

import com.ftn.OnlineFitness.model.Client;



public interface ClientDAO {

	public Client findOne(int id);

	public List<Client> findAll();
	
	public int save(Client client);

	public int update(Client client);

	public int delete(int id);
}
