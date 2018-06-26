package com.study.graph.impl;

import com.study.graph.Edge;

public class EdgeImp implements Edge {

	private final int first;
	public int getFirst() {
		return first;
	}
	public int getSecond() {
		return second;
	}
	private final int second;
	
	public EdgeImp(int first, int second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public String toString() {
		return "[" + first + ", " + second + "]";
	}
	public Incident incident() {
		return new Incident(first, second);
	}
	


}
