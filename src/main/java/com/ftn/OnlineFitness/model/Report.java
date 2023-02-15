package com.ftn.OnlineFitness.model;

import java.util.List;

public class Report {
	
	private int id;
	private String periodOfTime;
	private double income;
    private List<Trainer> bestRankedTrainers;
    private List<Trainer> highestPaidTrainers;
    
    
    
	public Report(int id, String periodOfTime, double income, List<Trainer> bestRankedTrainers,
			List<Trainer> highestPaidTrainers) {
		super();
		this.id = id;
		this.periodOfTime = periodOfTime;
		this.income = income;
		this.bestRankedTrainers = bestRankedTrainers;
		this.highestPaidTrainers = highestPaidTrainers;
	}
	
	
	
	
	
	public Report(int id, String periodOfTime, double income) {
		super();
		this.id = id;
		this.periodOfTime = periodOfTime;
		this.income = income;
	}





	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPeriodOfTime() {
		return periodOfTime;
	}
	public void setPeriodOfTime(String periodOfTime) {
		this.periodOfTime = periodOfTime;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public List<Trainer> getBestRankedTrainers() {
		return bestRankedTrainers;
	}
	public void setBestRankedTrainers(List<Trainer> bestRankedTrainers) {
		this.bestRankedTrainers = bestRankedTrainers;
	}
	public List<Trainer> getHighestPaidTrainers() {
		return highestPaidTrainers;
	}
	public void setHighestPaidTrainers(List<Trainer> highestPaidTrainers) {
		this.highestPaidTrainers = highestPaidTrainers;
	}
    
    
	

}
