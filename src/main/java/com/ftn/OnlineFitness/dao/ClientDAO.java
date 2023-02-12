package com.ftn.OnlineFitness.dao;
import java.util.List;

import com.ftn.OnlineFitness.model.Client;
import com.ftn.OnlineFitness.model.EGoals;
import com.ftn.OnlineFitness.model.ELanguage;
import com.ftn.OnlineFitness.model.EProps;



public interface ClientDAO {

	public Client findOne(int id);

	public List<Client> findAll();
	
	public int save(Client client);

	public int update(Client client);

	public int delete(int id);
	
	public List<ELanguage> getClientLanguages(int id);
	
	public List<EGoals> getClientGoals(int id);
	
	public List<EProps> getClientProps(int id);
	
	public Client findByEmail(String email);
	
	public Client findByPhoneNumber(String phoneNumber);
	
	public Client findByCardNumber(String cardNumber);
	
	public Client getByEmailAndPassword(String email,String password);


}
