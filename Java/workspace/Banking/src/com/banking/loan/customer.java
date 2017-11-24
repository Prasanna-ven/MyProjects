package com.banking.loan;

public class customer implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	public String name;
	public String address;	
	public String mobile;
	public int accountno;
	public int accountbal;
	public int loanamt;
	public int paidamt;
	   
	public void mailCheck(){
	      System.out.println("Mailing a check to " + name + " " + address);
	}

}
