package javatut;

public class Dog {
	Dog(String name){
		System.out.println("Name : "+name);
	}
	int age;
	String colour;
	int height;
	
	void bark(String a){
		if(a == "Rel"){
			System.out.println("tail waging!!!");
		}
		else
		{
			if (age <= 2){
				System.out.println("Rof rof rof!!!");
			}
			else
			{
				System.out.println("Bow Bow Bow!!!");
			}
		}
	}
	Boolean ishungry()
	{
		boolean hung = true;
		return hung;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog mydog = new Dog("Tommy");
		mydog.age = 3;
		mydog.colour = "Brown";
		mydog.height = 5;
		mydog.bark("Rel");
		boolean ishung = mydog.ishungry();
		System.out.println(ishung);
	}

}
