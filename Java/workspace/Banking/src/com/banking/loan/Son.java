package com.banking.loan;

public class Son extends Father{

	public int sonMoney = 100000;
	public int sonLand = 100;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int money = 0;
		Father Fobj1 = new Father();
		Son Sobj1 = new Son();
		System.out.println("Son money : "+Sobj1.sonMoney);
		money = Fobj1.fatherMoney + Sobj1.sonMoney;			
		money = Sobj1.addmoney(money);
		Fobj1.readingBooks("Fire in Heart",money);
	}
	
	public int addmoney(int a)
	{		
		int c = a;
		System.out.println("Total taxable money : "+a);
		return c;
	}

}
