/*******************************************************************************************************************
 * File Name: Input.java
 * Title:Input Routines
 * Milo Osterman
 * Pima Community College 
 ****************************************************************************************************************** 
 * This program accquires input from user with Scanner.
 ******************************************************************************************************************/

package mOsterman_HW3;

import java.util.*;

public class Input {
	//Global scanner declaration
	static Scanner sc = new Scanner(System.in);

	//Gets a valid string checking for null and spaces.
	public static String getString(String msg) {
		String input;
		
		System.out.println(msg);
		while(!sc.hasNext("[A-Za-z]*")) {
			sc.nextLine();
			System.out.println("Invalid entry. Try again.");
		}
		input = sc.next();
	
		return input;
	}

	// Method that accepts a message to display and minimum and maximum values to validate double.
	public static int getInteger(String msg, int min, int max) {
		int input = 0;
		
		do {
			System.out.println(msg);
			while (!sc.hasNextInt()) {
				sc.nextLine();
				System.err.println("Invalid integer. Try again.");
			}
			input = sc.nextInt();
		} while (input > max || input < min);
		int finalInput = input;
		sc.nextLine();
		
		return finalInput;
		
	}

	// Method that accepts a message to display and minimum and maximum values to validate double.
	public static double getDouble(String msg, double min, double max) {
		double input = 0;
		do {
			System.out.println(msg);
			while (!sc.hasNextDouble()) {
				sc.nextLine();
				System.err.println("Invalid double. Try again.");
			}
			input = sc.nextDouble();
		} while (input > max || input < min);
		double finalInput = input;
		sc.nextLine();
		
		return finalInput;
		
	}
	
	//Generates a random number between low and high
	public static int getRandomNumber(int low, int high) {
		return (int) (Math.random() * ((high + 1) - low)) + low;
		
	}

}//end class
