package com.felixmilea.discretestructures;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Calculator {

	private final static HashMap<Long, BigInteger> factorialCache = new HashMap<>();
	
	public static int calculate(int n, double err) {
		err = 1 - err;
		double p = 0.5;
		double k = n * p;
		
		double totalPerms = Math.pow(1/p, n);
		
		TreeMap<Integer, Double> results = new TreeMap<>();
		
		int ik = (int) Math.floor(k);
		int maxIts = Math.min(ik, n - ik);
		
		System.out.println();
		System.out.println("expected: " + k);
		System.out.println("perms   : " + totalPerms);
		
		for (int i = 0; i < maxIts && sum(results) < err; i++) {
			int above, below;
			
			// if is even number
			if (k == Math.floor(k)) {
				below = (int) k - i;
				above = (int) k + i;
			} else {
				below = (int) Math.floor(k) - i;
				above = (int) Math.ceil(k) + i;
			}
			
			results.put(below, choose(n, below) / totalPerms);
			results.put(above, choose(n, above) / totalPerms);
		}
		
		System.out.println();
		for (Map.Entry<Integer,Double> entry : results.entrySet()) {
			System.out.println((entry.getKey() < 10 ? " " : "") + entry.getKey() + ": " + entry.getValue());
		}

		System.out.println("\nProb Sum: " + sum(results));
		
		return 0;
	}

	public static int calculateDice(int n, double err) {
		err = 1 - err;
		double p = 1d / 6;
		double k = n * p;
		
		double totalPerms = Math.pow(6, n);
		
		TreeMap<Integer, Double> results = new TreeMap<>();
		
		int ik = (int) Math.floor(k);
		int maxIts = Math.max(Math.min(ik, n - ik), 1);
		
		System.out.println();
		System.out.println("expected: " + k);
		System.out.println("perms   : " + totalPerms);
		
		for (int i = 0; i < maxIts && sum(results) < err; i++) {
			int above, below;
			
			// if is even number
			if (k == Math.floor(k)) {
				below = (int) k - i;
				above = (int) k + i;
			} else {
				below = (int) Math.floor(k) - i;
				above = (int) Math.ceil(k) + i;
			}
			
			results.put(below, choose(n, below) * Math.pow(p,below) * Math.pow(1-p,n-below));
			results.put(above, choose(n, above) * Math.pow(p,above) * Math.pow(1-p,n-above));
		}
		
		System.out.println();
		for (Map.Entry<Integer,Double> entry : results.entrySet()) {
			System.out.println((entry.getKey() < 10 ? " " : "") + entry.getKey() + ": " + entry.getValue());
		}

		System.out.println("\nProb Sum: " + sum(results));
		
		return 0;
	}
	
	private static double sum(Map<Integer, Double> results) {
		double sum = 0;
		
		for (double d : results.values()) {
			sum += d;
		}
		
		return sum;
	}
	
	public static double choose(int top, int bottom) {
		return choose((long) top, (long) bottom);
	}
	
	public static double choose(Long top, Long bottom) {
		return factorial(top).divide((factorial(bottom).multiply(factorial(top - bottom)))).doubleValue();
	}
	
	public static BigInteger factorial(long n) {
		if (factorialCache.containsKey(n)) return factorialCache.get(n);
		
		BigInteger fact = n == 1 || n == 0 ? BigInteger.valueOf(n) : factorial(n - 1).multiply(BigInteger.valueOf(n));
		factorialCache.put(n, fact);
		
		return fact;
	}
	
}
