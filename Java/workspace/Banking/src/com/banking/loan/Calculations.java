package com.banking.loan;

public class Calculations extends Operations{

	public Calculations(int fval, int sval) {
		super(fval, sval);		
		super.add();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		Calculations Cobj1 = new Calculations(10,30);
		Cobj1.mul();
	}

}
