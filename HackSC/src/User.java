import java.util.Scanner;

public class User {
	//data members
	public int id;
	public String name;
	
	public User(int number, String name){
		this.id = number;
		this.name = name;
		System.out.println("Class created!");
	}
	
	public String getName(){
		return this.name;
	}
	
	public static void main(String[] args){
		System.out.println("hello");

		System.out.println("please enter your name");
		Scanner scan = new Scanner(System.in);
		
		String nameFromUser = scan.nextLine();

		User gn = new User(1, nameFromUser);
		System.out.println("name is: " + gn.getName());
		
	}
}

