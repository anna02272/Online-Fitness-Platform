package com.ftn.OnlineFitness.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
	
	private int id;
	private Trainer trainer;
	private Client client;
	private boolean isFree;
	private LocalDateTime dateAndTime;
	private float price;
	private ERating rating;
	private String comment;
		
	public Appointment(int id,Trainer trainer,Client client, boolean isFree, LocalDateTime dateAndTime, float price, ERating rating, String comment) {
		super();
		this.id = id;
		this.trainer = trainer;
		this.client = client;
		this.isFree = isFree;
		this.dateAndTime = dateAndTime;
		this.price = price;
		this.rating = rating;
		this.comment = comment;
	}
	
	public Appointment(Trainer trainer, Client client, boolean isFree, LocalDateTime dateAndTime, float price,
			ERating rating, String comment) {
		super();
		this.trainer = trainer;
		this.client = client;
		this.isFree = isFree;
		this.dateAndTime = dateAndTime;
		this.price = price;
		this.rating = rating;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isFree() {
		return isFree;
	}
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public ERating getRating() {
		return rating;
	}
	public void setRating(ERating rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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

	@Override
	public String toString() {
		
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String dateTimeStr = dateAndTime.format(formatter);
	    return id + ";" + Boolean.toString(isFree) + ";" + dateTimeStr + ";" + price + ";" + rating + ";" + comment;
	}

}
