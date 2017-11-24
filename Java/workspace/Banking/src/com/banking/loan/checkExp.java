package com.banking.loan;

public class checkExp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			int[] arr = {10,20,30};
		
			System.out.println("Val 1 :"+arr[0]);
			System.out.println("Val 2 :"+arr[1]);
			System.out.println("Val 3 :"+arr[2]);
			//System.out.println("Val 4 :"+arr[4]);
		}
		catch (Exception a)
		{
			System.out.println("You ended in slug ! : "+a);
		}
		finally
		{
			System.out.println("Completed");
		}
		
		try
		{
			int val = 0;
			if(val ==0)
			{
				throw new NullPointerException();
			}
		}
		catch (Exception b)
		{
			System.out.println(b);
		}
		finally
		{
			System.out.println("Completed2");
		}
		
	}

}
