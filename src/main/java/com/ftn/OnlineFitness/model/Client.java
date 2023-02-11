package com.ftn.OnlineFitness.model;

import java.util.List;

public class Client extends User {
    private int height;
    private int weight;
    private String illnessOrConditions;
    private List<EGoals> goals;
    private List<EProps> props;
    private double waistCircumference; 
    private double stomachCircumference;
    

  

	public Client(String name, String surname, String email, String password, String phoneNumber, String address,
			String cardNumber, ELanguage nativeLanguage, List<ELanguage> additionalLanguages, ERole role, int height,
			int weight, String illnessOrConditions, List<EGoals> goals, List<EProps> props, double waistCircumference,
			double stomachCircumference) {
		super(name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, additionalLanguages,
				role);
		this.height = height;
		this.weight = weight;
		this.illnessOrConditions = illnessOrConditions;
		this.goals = goals;
		this.props = props;
		this.waistCircumference = waistCircumference;
		this.stomachCircumference = stomachCircumference;
	}

	public Client(int id, String name, String surname, String email, String password, String phoneNumber,
			String address, String cardNumber, ELanguage nativeLanguage, List<ELanguage> additionalLanguages,
			ERole role, int height, int weight, String illnessOrConditions, List<EGoals> goals, List<EProps> props,
			double waistCircumference, double stomachCircumference) {
		super(id, name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, additionalLanguages,
				role);
		this.height = height;
		this.weight = weight;
		this.illnessOrConditions = illnessOrConditions;
		this.goals = goals;
		this.props = props;
		this.waistCircumference = waistCircumference;
		this.stomachCircumference = stomachCircumference;
	}
	public Client(int id, String name, String surname, String email, String password, String phoneNumber,
			String address, String cardNumber, ELanguage nativeLanguage, 
			ERole role, int height, int weight, String illnessOrConditions, 
			double waistCircumference, double stomachCircumference) {
		super(id, name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, 
				role);
		this.height = height;
		this.weight = weight;
		this.illnessOrConditions = illnessOrConditions;
		this.waistCircumference = waistCircumference;
		this.stomachCircumference = stomachCircumference;
	}

	public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public String getIllnessOrConditions() {
        return illnessOrConditions;
    }
    
    public void setIllnessOrConditions(String illnessOrConditions) {
        this.illnessOrConditions = illnessOrConditions;
    }
    
    
    
    public List<EGoals> getGoals() {
		return goals;
	}

	public void setGoals(List<EGoals> goals) {
		this.goals = goals;
	}

	public List<EProps> getProps() {
		return props;
	}

	public void setProps(List<EProps> props) {
		this.props = props;
	}

	public double getWaistCircumference() {
        return waistCircumference;
    }
    
    public void setWaistCircumference(double waistCircumference) {
        this.waistCircumference = waistCircumference;
    }
    
    public double getStomachCircumference() {
        return stomachCircumference;
    }
    
    public void setStomachCircumference(double stomachCircumference) {
        this.stomachCircumference = stomachCircumference;
    }
    
}
