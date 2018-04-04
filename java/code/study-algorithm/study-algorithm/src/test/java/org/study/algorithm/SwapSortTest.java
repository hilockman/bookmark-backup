package org.study.algorithm;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SwapSortTest {

	@Test
	public void correct() {
	
		
		
		for (int i = 0; i < 10000; i++) {
		
			Integer[] array = Utils.randomArray(1000);		
			
			Integer[] array1 = Arrays.copyOf(array, array.length);
		    SwapSort.sort(array);
		    Arrays.sort(array1);
		    assertArrayEquals(array, array1);
		    //Util.print(array);
		}
		
	}
	
	@Test
	public void sortTest() {
		Integer [] array = {3,5,9,4,7,2,1};
		System.out.println("raw arary = "+Util.toString(array));
		SwapSort.sort(array);
		System.out.println("sorted array = "+Util.toString(array));
	}
}
