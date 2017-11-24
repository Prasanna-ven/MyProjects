package com.banking.loan;

import java.io.*;

public class loanWriter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		customer cobj = new customer();
		cobj.name = "Prasanna";
		cobj.address = "48 3rd sector, HBR Layout, Bangalore";
		cobj.mobile = "9790932969";
		cobj.accountno = 123456789;
		cobj.accountbal = 100000000;
		cobj.loanamt = 50;
		cobj.paidamt = 50;
		try{
			FileOutputStream fout = new FileOutputStream ("D:\\Prasanna\\My_work\\Java\\customer.txt");
			ObjectOutputStream obj = new ObjectOutputStream (fout);
			obj.writeObject(cobj);
			obj.close();
			fout.close();
			System.out.printf("Serialized data is saved in D:\\Prasanna\\My_work\\Java");
		}catch (Exception a)
		{
			a.printStackTrace();
		}
	}

}
