package com.ftn.OnlineFitness.model;

import java.util.List;

public abstract class User {
	
	private int Id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String phoneNumber;
	private String address;
	private String cardNumber;
	private ELanguage nativeLanguage;
	private List<ELanguage> additionalLanguages;
	private ERole role;
	
	public User() {}
	
	
	public User(int id, String name, String surname, String email, String password, String phoneNumber, String address,
			String cardNumber, ELanguage nativeLanguage, List<ELanguage> additionalLanguages, ERole role) {
		super();
		Id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.cardNumber = cardNumber;
		this.nativeLanguage = nativeLanguage;
		this.additionalLanguages = additionalLanguages;
		this.role = role ;
	}
	
	public User( String name, String surname, String email, String password, String phoneNumber, String address,
			String cardNumber, ELanguage nativeLanguage, List<ELanguage> additionalLanguages, ERole role) {
		super();
	
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.cardNumber = cardNumber;
		this.nativeLanguage = nativeLanguage;
		this.additionalLanguages = additionalLanguages;
		this.role = role ;
	}


	public User(int id, String name, String surname, String email, String password, String phoneNumber, String address,
			String cardNumber, ELanguage nativeLanguage, ERole role) {
		super();
		Id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.cardNumber = cardNumber;
		this.nativeLanguage = nativeLanguage;
		this.role = role;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public ELanguage getNativeLanguage() {
		return nativeLanguage;
	}
	public void setNativeLanguage(ELanguage nativeLanguage) {
		this.nativeLanguage = nativeLanguage;
	}
	public List<ELanguage> getAdditionalLanguages() {
		return additionalLanguages;
	}
	public void setAdditionalLanguages(List<ELanguage> additionalLanguages) {
		this.additionalLanguages = additionalLanguages;
	}
	


}
