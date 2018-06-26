package org.study.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.naming.LimitExceededException;

public class Heap<T> {
	private T[] array; 
	private int length;
	private final Comparator<T> cmp;
	public Heap(T[] array) {
		this.array = Arrays.copyOf(array, array.length);
		length = array.length;
		
		cmp = (l, r)->{ return ((Comparable<T>)l).compareTo(r);};
		HeapSort.sort(array, cmp);
		
		
	}
	
	public Heap(T[] array,  Comparator<? super T> c) {
		this.array = Arrays.copyOf(array, array.length);
		length = array.length;
		this.cmp = (Comparator<T>) c;
		HeapSort.sort(array, c);
	}
	
	public T maximum() {
		return array[0];
	}
	
	public T extractMax() {
		if (length < 1)
			throw new OutOfMemoryError("array.lenght == 0.");
		
		T max = array[0];
		length--;
		array[0] = array[length - 1];
		HeapSort.maxHeapify(array, length, 0, cmp);
		return max;
	}
	
	public void increateKey(int i, T key) {
		if (array[i] != null && cmp.compare(key, array[i]) < 0) {
			throw new RuntimeException("new key is smaller than current key");
		}
		
		array[i] = key;
		int parent = (i - 1)>>1;
		while (i > 0 && cmp.compare(array[parent], array[i]) < 0) {
			HeapSort.swap(array, i, parent);
			i = parent;
			parent = (i - 1)>>1;
		}
	}
	
	public void insert(T key) {
		array = Arrays.copyOf(array, array.length + 1);
		length++;
		increateKey(length - 1, key); 
	}

	public T[] getArray() {
		return array;
	}
}
