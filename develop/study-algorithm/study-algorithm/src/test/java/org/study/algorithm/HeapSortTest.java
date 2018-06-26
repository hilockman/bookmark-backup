package org.study.algorithm;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class HeapSortTest {

	@Test
	public void maxHeapifyTest() {
		int [] array = {3,5,9,4,7,2,1};
		System.out.println("raw arary = "+Util.toString(array));
		HeapSort.buildMaxHeap(array);
		System.out.println("max heap = "+Util.toString(array));
	}
	
	@Test
	public void minHeapifyTest() {
		int [] array = {3,5,9,4,7,2,1};
		System.out.println("raw arary = "+Util.toString(array));
		HeapSort.buildMinHeap(array);
		System.out.println("min heap = "+Util.toString(array));
	}
	
	@Test
	public void heapSortTest() {
		int [] array = {3,5,9,4,7,2,1};
		System.out.println("raw arary = "+Util.toString(array));
		HeapSort.sort(array);
		System.out.println("sorted array = "+Util.toString(array));
	}
	
	
	@Test
	public void correct() {
	
		
		
		for (int i = 0; i < 10; i++) {
		
			Integer[] array = Utils.randomArray(100);		
			
			Integer[] array1 = Arrays.copyOf(array, array.length);
			HeapSort.sort(array);
		    Arrays.sort(array1);
		    assertArrayEquals(array, array1);
		   // Util.print(array);
		}
		
	}
	
	
	@Test
	public void increateKey() {
		System.out.println("----------------------increateKey--------------------------------------");
		Integer [] array = {3,5,9,4,7,2,1};
		System.out.println("raw arary = "+Util.toString(array));
		Heap h = new Heap(array);
		System.out.println("sorted array = "+Util.toString(h.getArray()));
		h.increateKey(2, 100);
		System.out.println("increase key :"+100+ ", array = "+Util.toString(h.getArray()));
		h.insert(99);
		System.out.println("insert key :"+99+ ", array = "+Util.toString(h.getArray()));
		System.out.println("----------------------increateKey--------------------------------------");
	}
	
	
}
