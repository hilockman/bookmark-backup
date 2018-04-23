package com.study.graph;

public interface Matrix {

	void print();

	Matrix turn();
	
	Matrix mmul(Matrix other);
	
	int[] rank();
}
