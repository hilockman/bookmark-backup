package com.study.graph.impl;

import com.study.graph.Tree;

public class TreeImp implements Tree {

	private final int[] branches;
	
	public TreeImp(int [] branches) {
		this.branches = branches;
	}
	public int[] getBranches() {
		return branches;
	}

}
