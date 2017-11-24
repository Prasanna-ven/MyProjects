package com.banking.loan;

import java.io.*;

public class SerializeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Employee e = new Employee();
	      e.name = "Reyan Ali";
	      e.address = "Phokka Kuan, Ambehta Peer";
	      e.SSN = 11122333;
	      e.number = 101;
	      
	      try{
	         FileOutputStream fileOut =
	         new FileOutputStream("D:\\Prasanna\\My_work\\Java\\employee.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(e);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in D:\\Prasanna\\My_work\\Java");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}

}
