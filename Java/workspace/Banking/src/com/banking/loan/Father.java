package com.banking.loan;

public class Father {
	public int fatherMoney = 1000000;
	public int fatherLand = 100;
	// Private variable can only be accessed within the class 
	private static int fatherBlackMoney = 100000;
	public void readingBooks(String bookName , int a)
	{		
		System.out.println("Book Name : "+bookName);	
		Father Fobj1 = new Father();
		Fobj1.calMoney(a);
		
	}
	
	public void calMoney(int a)
	{
		int c;
		System.out.println("Black Money : "+fatherBlackMoney);			
		c = fatherBlackMoney + a;
		System.out.println("Net Worth : "+c);
	}
}
