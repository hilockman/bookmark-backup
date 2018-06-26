package org.study;

import java.util.Random;

public class Utils {
	public static void fillMatrix(double [][] array) {
		Random r = new Random();
		r.setSeed(1000);
		for (double [] a :  array) {
			for (int i = 0; i < a.length; i++) {
				a[i] = r.nextDouble();
			}
		}
	}
	
}
