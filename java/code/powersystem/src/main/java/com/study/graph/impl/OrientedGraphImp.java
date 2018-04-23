package com.study.graph.impl;

import com.study.graph.Edge;
import com.study.graph.Matrix;
import com.study.graph.Vertex;
import com.study.graph.OrientedGraph;
import com.study.graph.Tree;

public class OrientedGraphImp implements OrientedGraph {

	private final Vertex [] nodes;
	private final Edge[] branches;
	
	public OrientedGraphImp(int [] nodes, int[][] branches) {
		this.nodes = new NodeImp[nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			this.nodes[i] = new NodeImp(nodes[i]);
		};
		
		this.branches = new EdgeImp[branches.length];
		for (int i = 0; i < branches.length; i++) {
			this.branches[i] = new EdgeImp(branches[i][0], branches[i][1]);
		}
	}
	public Matrix nodeBrachIncidenceMatrix() {
		int [][] array = new int [nodes.length][branches.length];
		for (int i = 0; i < array.length; i++) {
			

			for (int j = 0; j < branches.length; j++) {
				EdgeImp b = (EdgeImp) branches[j];
				array[b.getFirst()-1][j] = 1;
				array[b.getSecond()-1][j] = -1;
			}
		} 
		return new IntegerMatrix(array);
	}



	public Matrix basicCutsetBrachIncidenceMatrix() {
		// TODO Auto-generated method stub
		return null;
	}
	public Vertex [] getNodes() {
		return nodes;
	}
	public Edge[] getBranches() {
		return branches;
	}
	
	private int [] branchEdges(int [] treeEdges) {
		int [] edges = new int[branches.length - treeEdges.length];
		int j = 0;
		int k = 0;
		for (int i = 0; i < branches.length; i++) {
			if (treeEdges[j] < i+1) {
				j++;
			} else if (treeEdges[j] == i+1) {
				continue;
			}
			
			edges[k++] = i + 1;
		}
		return edges;
	}
	
	@Override
	public Matrix basicLoopBranchIncidenceMatrix(int [] treeEdges) {
		
		int [] branches = branchEdges(treeEdges);
		return null;
	}

}
