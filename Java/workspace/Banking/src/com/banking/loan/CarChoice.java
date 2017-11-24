package com.banking.loan;

import java.util.Scanner;

public class CarChoice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		System.out.println("Enter the Car Manufacturer :");
		Scanner Sobj1 = new Scanner(System.in);
		String userinput = Sobj1.nextLine();
		Sobj1.close();
		Car cobj;
		if(userinput.equals("Hyundai"))
		{
			cobj = new HyundaiCar();
			cobj.carname(userinput,"Elite i20");
			cobj.start();
			cobj.stop();
			cobj.Engine();
			cobj.Fueltype();
		}
		else if(userinput.equals("Honda"))
		{
			cobj = new HondaCar();
			cobj.carname(userinput,"Honda Civic");
			cobj.start();
			cobj.stop();
			cobj.Engine();
			cobj.Fueltype();
		}
		else if(userinput.equals("LandRover"))
		{
			cobj = new LandroverCar();
			cobj.carname(userinput,"Discover Sport");
			cobj.start();
			cobj.stop();
			cobj.Engine();
			cobj.Fueltype();
		}
		else
		{
			System.out.println("Invalid choice of Car !");
		}
	}
}
