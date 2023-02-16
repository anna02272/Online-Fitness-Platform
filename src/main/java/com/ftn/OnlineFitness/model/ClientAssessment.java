
package com.ftn.OnlineFitness.model;

import java.util.List;

public class ClientAssessment extends User {
    
    private Client client;
    private Trainer trainer;
    private ERating rating;
    public String comment; 
    
	public ClientAssessment() {}

	 

	public ClientAssessment( Client client,Trainer trainer, ERating rating, String comment) {
		super();
		
		this.client = client;
		this.trainer = trainer;
		this.rating = rating;
		this.comment = comment;
	}



	public ClientAssessment(Client client, ERating rating, String comment) {
		super();
		this.client = client;
		this.rating = rating;
		this.comment = comment;
	}



	public ERating getRating() {
		return rating;
	}



	public void setRating(ERating rating) {
		this.rating = rating;
	}

 

	public Trainer getTrainer() {
		return trainer;
	}



	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}



	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
 

	
    
}
