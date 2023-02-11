package com.ftn.OnlineFitness.model;

public enum ERating {
	 ONE(1),
	 TWO(2),
	 THREE(3),
	  FOUR(4),
	  FIVE(5);
	  
	 private final int value;
	 
     ERating(int value) {
		 this.value=value;
	 }
	 
	public int getValue() {
	        return value;
	    }

}
