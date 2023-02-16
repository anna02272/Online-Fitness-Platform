
package com.ftn.OnlineFitness.model;

import java.util.List;

public class TrainerAssessment extends User {
    private Trainer trainer;
    private Client client;
    private ERating rating;
    public String comment; 
    
	public TrainerAssessment() {}

	 

	public TrainerAssessment(Trainer trainer, Client client, ERating rating, String comment) {
		super();
		this.trainer = trainer;
		this.client = client;
		this.rating = rating;
		this.comment = comment;
	}



	public TrainerAssessment(Trainer trainer, ERating rating, String comment) {
		super();
		this.trainer = trainer;
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
