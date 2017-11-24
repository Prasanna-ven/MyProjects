package com.banking.loan;

public class AudiCar implements CarInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AudiCar Aobj1 = new AudiCar();
		Aobj1.start();
		Aobj1.stop();
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Start with zoom : ");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Stop with honk : ");
	}

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int modelNum(String carModel) {
		// TODO Auto-generated method stub
		return 0;
	}

}
