package com.ftn.OnlineFitness.model;

import java.util.List;

public class Admin extends User {

	
	
	public Admin(int id, String name, String surname, String email, String password, String phoneNumber, String address,
			String cardNumber, ELanguage nativeLanguage, List<ELanguage> additionalLanguages, ERole role) {
		super(id, name, surname, email, password, phoneNumber, address, cardNumber, nativeLanguage, additionalLanguages, role);
		// TODO Auto-generated constructor stub
	}

	public boolean CheckData() {
		//TODO provera podataka 
		return true;
	}
	
	public void activateAccount() {
		//TODO aktivacija naloga
	}
	
	

}
