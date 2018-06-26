package com.study.graph.impl;

import junit.framework.Assert;

import com.study.graph.Matrix;

public class IntegerMatrix implements Matrix {

	public final int[][] array;
	public IntegerMatrix(int[][] array) {
		this.array = array;
	}
	
	public IntegerMatrix(int[] rank) {
		this.array = new int[rank[0]][rank[1]];
	}
	
	@Override
	public void print() {
		StringBuilder b = new StringBuilder();
		b.append('[').append('\n');
		for (int i = 0; i < array.length; i++) {
			int[] a = array[i];
			for (int j = 0; j < a.length; j++) {
				if (j > 0)
					b.append(",");
				b.append(a[j]);
			}
			b.append('\n');
		}
		b.append(']').append('\n');;
		System.out.print(b.toString());
	}

	@Override
	public Matrix turn() {
		int[][] array1 = new int[array[0].length][array.length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array1[j][i] = array[i][j];
				
			}
		}
		return new IntegerMatrix(array1);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Matrix mmul(Matrix o) {
		IntegerMatrix other = (IntegerMatrix) o;
		Assert.assertTrue(this.rank()[1] == other.rank()[0]);
		int n = this.rank()[0];
		int m = this.rank()[1];
		int p = other.rank()[1];
		int[] rank = {n, p};
		IntegerMatrix rt = new IntegerMatrix(rank);
		

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				int sum = 0;
				for (int k = 0; k < m; k++) {
					sum += this.array[i][k]*other.array[k][j];
				}
				rt.array[i][j] = sum;
			}
		}
		return rt;
	}
	
//	private void putValue(int i, int j, int v) {
//		array[i][j] = v;
//	}
//	
//	private int getValue(int i, int j) {
//		return array[i][j];
//	}

	@Override
	public int[] rank() {
		if (array.length == 0) {
			int[] r =  {0, 0};
			return r;
		}
		
		int [] r = {array.length, array[0].length};
		return r;
	}
}
