package com.study.graph.impl;

import com.study.graph.Vertex;

public class NodeImp implements Vertex {

	@Override
	public String toString() {
		return "" + number  ;
	}

	private final int number;
	
	public NodeImp(int number) {
		this.number = number;
	}
	
	public int degree() {
		return 0;
	}
	
	public int getNumber() {
		return number;
	}

	
}
