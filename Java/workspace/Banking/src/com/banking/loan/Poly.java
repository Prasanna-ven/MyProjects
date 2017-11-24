package com.banking.loan;

import java.util.Scanner;

public class Poly {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Database Dobj1;
		Scanner Sobj1 = new Scanner(System.in);
		String userinput = Sobj1.nextLine();
		Sobj1.close();
		if(userinput.equals("Oracle"))
		{
			Dobj1 = new Oracle();
			Dobj1.dbconnection();
		}
		else
		{
			Dobj1 = new Mysql();
			Dobj1.dbconnection();
		}
	}

}
