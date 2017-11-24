package com.banking.loan;

public class HyundaiCar extends Car{

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		HyundaiCar Hobj1 = new HyundaiCar();
		Hobj1.carname("Elite i20");
		Hobj1.start();
		Hobj1.stop();
		Hobj1.Engine();
		Hobj1.Fueltype();
	}*/

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Start features of Hyundai !");
		System.out.println("1.) Start with vroom.");		
		System.out.println("2.) Button Start.");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Stop features of Hyundai !");
		System.out.println("3.) Stop with thug.");		
		System.out.println("4.) Button Stop.");
	}

	@Override
	public void Engine() {
		// TODO Auto-generated method stub
		System.out.println("Engine features of Hyundai !");
		System.out.println("5.) Engine 1200 CC.");		
	}

	@Override
	public void Fueltype() {
		// TODO Auto-generated method stub
		System.out.println("Fueltype features of Hyundai !");
		System.out.println("6.) Petrol Ignation.");		
	}
	
}
