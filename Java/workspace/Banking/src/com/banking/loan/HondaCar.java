package com.banking.loan;

public class HondaCar extends Car {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Start features of Honda !");
		System.out.println("1.) Start with zoom.");		
		System.out.println("2.) Key Start.");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Stop features of Honda!");
		System.out.println("3.) Stop with thup.");		
		System.out.println("4.) Key Stop.");
	}

	@Override
	public void Engine() {
		// TODO Auto-generated method stub
		System.out.println("Engine features of Honda !");
		System.out.println("5.) Engine 1000 CC.");		
	}

	@Override
	public void Fueltype() {
		// TODO Auto-generated method stub
		System.out.println("Fueltype features of Honda !");
		System.out.println("6.) Diesel Ignation.");		
	}
	

}
