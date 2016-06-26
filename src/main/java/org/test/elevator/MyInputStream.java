package org.test.elevator;

import java.util.Scanner;

public class MyInputStream {
	
	private static Scanner myScanner = new Scanner(System.in);

	public static String nextLine() {
		// User interactive mode
		System.out.println("Please enter your command: ");
		String userEntered = myScanner.nextLine();
		return userEntered;
	}

}
