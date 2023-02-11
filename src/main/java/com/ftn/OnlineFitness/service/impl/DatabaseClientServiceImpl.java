package com.ftn.OnlineFitness.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.OnlineFitness.dao.ClientDAO;
import com.ftn.OnlineFitness.model.Client;
import com.ftn.OnlineFitness.service.ClientService;

@Service
public class DatabaseClientServiceImpl implements ClientService{

	@Autowired
	private ClientDAO clientDAO;

	@Override
	public Client findOne(int id) {
		return clientDAO.findOne(id);
	}

	@Override
	public List<Client> findAll() {
		return clientDAO.findAll();
	}

	@Override
	public Client save(Client client) {
		clientDAO.save(client);
		return client;
	}

	@Override
	public Client update(Client client) {
		clientDAO.update(client);
		return client;
	}

	@Override
	public Client delete(int id) {
		Client client = clientDAO.findOne(id);
		if(client != null) {
			clientDAO.delete(id);
		}
		return client;
	}

}

