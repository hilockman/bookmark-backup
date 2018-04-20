package org.study;

import java.util.Random;

import org.junit.Test;

public class RandomTest {

	@Test
	public void testSeed() {
	      // create random object
	      Random randomno = new Random();

	      // setting seed
	      randomno.setSeed(30);

	      // value after setting seed
	      System.out.println("Object after seed: " + randomno.nextInt());
	}
}
