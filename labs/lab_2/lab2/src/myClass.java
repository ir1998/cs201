import java.util.Scanner;

public class myClass {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your username:");
		String name = in.nextLine();
		in.close();
		System.out.println("Hi " + name);
	}
}
