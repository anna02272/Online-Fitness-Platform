
package com.ftn.OnlineFitness.model;

import java.util.List;


public class Trainer extends User {
    private String certificate;
    private String diploma;
    private String title;
    public boolean isActive;
    private double salary;
    
    
    public Trainer() {}

    

	public Trainer(String name, String surname, String email, String password, String phoneNumber, String address,
			String cardNumber, ELanguage nativeLanguage, List<ELanguage> additionalLanguages, ERole role,
			String certificate, String diploma, String title, boolean isActive, double salary) {
		super(name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, additionalLanguages,
				role);
		this.certificate = certificate;
		this.diploma = diploma;
		this.title = title;
		this.isActive = isActive;
		this.salary = salary;
	}

	public Trainer(int id, String name, String surname, String email, String password, String phoneNumber, String address,
			String cardNumber, ELanguage nativeLanguage, ERole role,
			String certificate, String diploma, String title, boolean isActive, double salary) {
		super(id, name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage,role);
		this.certificate = certificate;
		this.diploma = diploma;
		this.title = title;
		this.isActive = isActive;
		this.salary = salary;
	}

	public Trainer(int id, String name, String surname, String email, String password, String phoneNumber,
			String address, String cardNumber, ELanguage nativeLanguage, List<ELanguage> additionalLanguages,
			ERole role, String certificate, String diploma, String title, boolean isActive, double salary) {
		super(id, name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, additionalLanguages,
				role);
		this.certificate = certificate;
		this.diploma = diploma;
		this.title = title;
		this.isActive = isActive;
		this.salary = salary;
	}

	public String getCertificate() {
        return certificate;
    }
    
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	
    
}
