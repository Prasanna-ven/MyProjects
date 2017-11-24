package javatut;

import java.util.Date;

public class HWorld {

	public static void main(String []args){
	
		System.out.println("Hi There, I am learning Java quick!");
		System.out.println("\tThis is to test the\n \\ escape \rcharacters\f");
		
		
		Integer c = 10;
		int a; 
		int [] nu = {10,20,30,40};
		char [] chr = {'H','E','L','L','O'};
		String helloStr = new String(chr);
		System.out.println("String  : "+helloStr);
		System.out.println("length  : "+helloStr.length());
		Integer na = 20;
		Date da = new Date();
		System.out.println("Date : "+da);
		
		
		// while loop test
		/*while(c < 20){
			System.out.println("Value of C : "+c);
			c++;
		}
		
		//Do while test
		a = 10;
		do{
			
			System.out.println("Value of a : "+a);
			a++;
		}while (a<30);
		
		// for loop
		for(int i =0; i < 10; i++)
		{
			if(i == 4)
			{continue;}
			System.out.println("The value of i : "+i);
			
		}
		
		for (int i : nu)
		{
			switch (i)
			{
				case 10 :
					System.out.println("switch test 10");
					break;
				case 20 :
					System.out.println("switch test 20");
					break;
				case 30 : 
					System.out.println("switch test 30");
					break;
				case 40 :
					System.out.println("switch test 40");
					break;
			}
		
			System.out.println("test for each : "+i);
		}*/
	
		
	}	
	
}
