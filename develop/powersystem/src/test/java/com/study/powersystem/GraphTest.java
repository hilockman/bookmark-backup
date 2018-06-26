package com.study.powersystem;

import org.junit.Test;

import com.study.graph.Graph;
import com.study.graph.Matrix;
import com.study.graph.impl.OrientedGraphImp;

public class GraphTest {

	@Test
	public void testCreateGraph() {
		int[] nodes = {1,2,3,4,5};
		int[][] branches = {{1,2}, {3, 2}, {4,2}, {1,3},{3,4}, {5,4}, {1,5} };
		Graph  g = new OrientedGraphImp(nodes, branches);
		Matrix m = g.nodeBrachIncidenceMatrix();
		m.print();
	}
	
	@Test
	public void testTurn() {
		
	}
	
	@Test
	public void testMutiply() {
		
	}
}
