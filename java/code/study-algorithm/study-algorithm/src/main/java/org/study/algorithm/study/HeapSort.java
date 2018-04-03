package org.study.algorithm.study;

import java.util.Arrays;

public class HeapSort {

	private int left(int i) {
		return 2*i;
	}
	
	
	
	
	private void maxHeapify(int[] A, int i) {
		int l = i<<1;
		int r = l + 1;
		
		int largest;
		if (l <= A.length - 1 && A[l] > A[i]) 
			largest = l;
		 else
			largest = r;
		
		
		if (r <= A.length - 1 && A[r] > A[largest]) {
		    largest = r;
		}
		
		if (largest != i) {
			swap(A, i, largest);
			maxHeapify(A, largest);
		}
		
		
		
	}
	private void swap(int[] a, int i, int largest) {
		int temp = a[i];
		a[i] = a[largest];
		a[largest] = temp;
	}




	public static void sort(int[] arrays) {
		
	}
}
