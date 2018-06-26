package org.study.algorithm;

import org.junit.Test;

public class CountingSortTest {

	@Test
	public void test() {
		int [] array = {3,5,9,4,7,2,1,3,2,3,3,0};
		int [] array1 = new int[array.length];
		
		System.out.println("raw arary = "+Util.toString(array));
		CountingSort.sort(array, array1, 9);
		System.out.println("sorted array = "+Util.toString(array1));
	}
}
