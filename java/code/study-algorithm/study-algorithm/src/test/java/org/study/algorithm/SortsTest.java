package org.study.algorithm;

import java.util.Arrays;

import org.junit.Test;

public class SortsTest {

	
	public static final int count = 1000;
	public static final int size = 100000;

	
	
	@Test
	public void swapsort() {
	
		
		
		for (int i = 0; i < count; i++) {
		
			Integer[] array = Utils.randomArray(size);		
		    MergeSort.sort(array);

		}
		
	}
	

	@Test
	public void quicksort() {
	
		for (int i = 0; i < count; i++) {
			Integer[] array = Utils.randomArray(size);		
		    Arrays.sort(array);
		}
		
	}

	@Test
	public void parallelsort() {
	
		for (int i = 0; i < count; i++) {
			Integer[] array = Utils.randomArray(size);		
		    Arrays.parallelSort(array);
		}
		
	}

	@Test
	public void heapsort() {
	
		for (int i = 0; i < count; i++) {
			Integer[] array = Utils.randomArray(size);		
		    HeapSort.sort(array);
		}
		
	}
}
