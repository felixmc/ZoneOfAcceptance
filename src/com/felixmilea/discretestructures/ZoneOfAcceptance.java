package com.felixmilea.discretestructures;

import java.util.Scanner;

public class ZoneOfAcceptance {
	private final static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome to ZoneOfAcceptance Calculator v0.1");
		System.out.println("  by Felix Milea-Ciobanu\n");
		
		System.out.println("Choose the type of calculation: ");
		System.out.println("1. Coin");
		System.out.println("2. Die");
		
		System.out.println();
		int calcType = getInt("calculation type", 1, 2);
		
		System.out.println();
		int trials = getInt("number of trials", 1, Integer.MAX_VALUE);
		
		System.out.println();
		double acErr = getAcceptError();
		
		if (calcType == 1)
			Calculator.calculate(trials, acErr);
		else
			Calculator.calculateDice(trials, acErr);
	}
	
	public static double getAcceptError() {
		Double acErr = null;
		
		do {
			if (acErr != null)
				System.out.println("Invalid acceptable error.\n");
			System.out.print("Enter acceptable error (0.0 to 1.0): ");
			try {
				acErr = Double.parseDouble(scanner.next());
			} catch (NumberFormatException e) {
				acErr = -1d;
			}
		} while (acErr < 0 || acErr > 1);
		
		return acErr;
	}
	
	public static int getInt(String label, int min, int max) {
		Integer val = null;
		
		do {
			if (val != null)
				System.out.println("Invalid value. Must be between "+min+" and "+max+" (inclusive) \n");
			System.out.print("Enter "+label+": ");
			try {
				val = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				val = -1;
			}
		} while (val < min || val > max);
		
		return val;
	}

}
