package org.study.algorithm;

import java.util.Comparator;


public class HeapSort {
	
	public static void maxHeapify(int[] A, int len, int i0) {
		
		int i = i0;
		int largest = i0;
		do {
			
			int r = (i+1)<<1;
			int l = r - 1;
			
			
			
			if (l <= len - 1 && A[l] > A[i]) 
				largest = l;
			 else
				largest = i;
			
			
			if (r <= len - 1 && A[r] > A[largest]) {
			    largest = r;
			}
			
			if (largest != i) {
				swap(A, i, largest);	
				i = largest;
				//maxHeapify(A, largest);
			} else {
				break;
			}
		} while (true); 
		
	}
	
	public static void swap(int[] a, int i, int j) {
		if (i == j)
			return;
		
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}


	public static void buildMaxHeap(int [] array) {
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			maxHeapify(array, array.length, i);
		}
	}
	
	
	public static void buildMinHeap(int [] array) {
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			minHeapify(array, array.length, i);
		}
	}
	
	private static void minHeapify(int[] A, int len, int i0) {	
		int i = i0;
		int min = i0;	
		do {
			
			int r = (i+1)<<1;
			int l = r - 1;
			if (l <= len - 1 && A[l] < A[i]) 
				min = l;
			 else
				min = i;
			
			
			if (r <= len - 1 && A[r] < A[min]) {
			    min = r;
			}
			
			if (min != i) {
				swap(A, i, min);
				i = min;
				//minHeapify(A, min);
			} else {
				break;
			}
		} while (true);

	}

	public static void sort(int[] array) {
		buildMaxHeap(array);
		int len = array.length;
		for (int i = array.length - 1; i >= 0; i--) {
			swap(array, 0, i);
			len--;
			maxHeapify(array, len, 0);
		}
	}
	
	public static void sort(Integer[] array) {
		sort(array, (l, r)->{ return l.compareTo(r);});
	}
	
	public static <T> void sort(T[] array,  Comparator<? super T> c) {
		buildMaxHeap(array, c);
		int len = array.length;
		for (int i = array.length - 1; i >= 0; i--) {
			swap(array, 0, i);
			len--;
			maxHeapify(array, len, 0, c);
		}		
	}
	
	
	public static <T> void swap(T[] a, int i, int j) {
		if (i == j)
			return;
		
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	private static <T> void buildMaxHeap(T [] array,  Comparator<? super T> c) {
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			maxHeapify(array, array.length, i, c);
		}
	}
	
	public static <T> void maxHeapify(T[] A, int len, int i0,  Comparator<? super T> c) {		
		int i = i0;
		int largest = i0;
		while(true) {
			
			int r = (i+1)<<1;
			int l = r - 1;
			
			
			
			if (l <= len - 1 && c.compare(A[l] , A[i]) > 0) 
				largest = l;
			 else
				largest = i;
			
			
			if (r <= len - 1 && c.compare(A[r] , A[largest]) > 0) {
			    largest = r;
			}
			
			if (largest != i) {
				swap(A, i, largest);	
				i = largest;
			} else {
				break;
			}
		}  	
	}
}
