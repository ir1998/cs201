package secondTry;

import java.util.Scanner;

public class printOut {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your username:");
		String name = in.nextLine();
		in.close();
		System.out.println("Hi " + name);
	}
}
