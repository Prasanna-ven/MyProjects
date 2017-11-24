package com.banking.loan;

public class HomeLoan {
	// Static variable can be used directly or by creating objects  
	public static int totLoan = 100000;
	// Non Static variable can be used only by creating objects
	public int instVar1 = 10;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Loan Calculator !");
		HomeLoan Hobj1 = new HomeLoan();
		Hobj1.instVar1 = 250;
		System.out.println("First Instalment : "+Hobj1.instVar1);
		totLoan = Hobj1.addCalc(totLoan, Hobj1.instVar1);
		System.out.println("Loan Balance :"+totLoan);		
		
		Hobj1.addCalc(totLoan, Hobj1.instVar1);
		HomeLoan Hobj2 = new HomeLoan();
		Hobj2.instVar1 = 750;
		System.out.println("Second Instalment :"+Hobj2.instVar1);
		totLoan = Hobj2.addCalc(totLoan, Hobj2.instVar1);
		System.out.println("Loan Balance :"+totLoan);
	}
	
	public int addCalc(int a, int b){
		int c = a-b;		
		return c;
	}

}
