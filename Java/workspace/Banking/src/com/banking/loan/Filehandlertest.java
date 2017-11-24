package com.banking.loan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Filehandlertest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream filin = new FileInputStream(new File("/home/prasanna/E-Books/H-Series/JPA/Java_03072016.txt"));
		FileOutputStream filout = new FileOutputStream(new File("/home/prasanna/E-Books/H-Series/JPA/Java_03072016_2.txt"));
		int val;
		while((val = filin.read()) != -1)
		{
			filout.write(val);
		}
		filin.close();
		filout.close();
		System.out.println("Completed");
		
		//Char
		FileReader filrd = new FileReader(new File("/home/prasanna/E-Books/H-Series/JPA/Java_03072016.txt"));
		FileWriter filwr = new FileWriter(new File("/home/prasanna/E-Books/H-Series/JPA/Java_03072016_3.txt"));
		int val1;
		while((val1 = filrd.read()) != -1)
		{
			filwr.write(val1);
		}
		filrd.close();
		filwr.close();
		System.out.println("Character Completed");
		//Buffer stream
		BufferedReader buffrd = new BufferedReader(new FileReader(new File("/home/prasanna/E-Books/H-Series/JPA/Java_03072016.txt")));
		BufferedWriter buffwr = new BufferedWriter(new FileWriter(new File("/home/prasanna/E-Books/H-Series/JPA/Java_03072016_4.txt")));
		String val2;
		while((val2 = buffrd.readLine()) != null)
		{
			buffwr.write(val2);
		}
		buffrd.close();
		buffwr.close();
		System.out.println("Buffer Completed");
	}

}
