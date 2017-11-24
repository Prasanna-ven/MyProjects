package com.banking.loan;

public abstract class Car {

	public abstract void start();
	public abstract void stop();
	public abstract void Engine();
	public abstract void Fueltype();
	
	public void carname(String carMaker, String carModel)
	{
		System.out.println(carMaker+" Car Model : "+carModel);		
	}
	
}
