package com.banking.loan;

import java.io.*;

public class loanReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int overdue;
		int networth;
		customer c = null;
		try{
			FileInputStream fin = new FileInputStream("D:\\Prasanna\\My_work\\Java\\customer.txt");
			ObjectInputStream obj = new ObjectInputStream(fin);
			c = (customer) obj.readObject();
			obj.close();
			fin.close();
		}catch(IOException i){
	         i.printStackTrace();
	         return;
	    }catch(ClassNotFoundException a){
	         System.out.println("Customer class not found");
	         a.printStackTrace();
	         return;
	    }
	      System.out.println("Deserialized Employee...");
	      System.out.println("Name: " + c.name);
	      System.out.println("Address: " + c.address);
	      System.out.println("Mobile: " + c.mobile);
	      System.out.println("Accountno.: " + c.accountno);
	      System.out.println("Account Bal. : " + c.accountbal);	
	      System.out.println("Loan Amt : " + c.loanamt);
	      System.out.println("paid Amt : " + c.paidamt);
	      overdue = c.loanamt -  c.paidamt;
	      System.out.println("Overdue Amt : " + overdue);
	      networth = c.accountbal - overdue;
	      System.out.println("Net Worth : " + networth);
	}

}
