package com.banking.loan;

public class Operations {

	public int firstVal;
	public int secondVal;
	
	public Operations(int fval, int sval){
		this.firstVal = fval;
		this.secondVal = sval;
	}
	
	public void add(){
		System.out.println(firstVal+secondVal);
	}

	public void mul(){
		System.out.println(firstVal*secondVal);
	}
	
}
