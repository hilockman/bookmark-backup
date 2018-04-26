package com.study.graph;

import java.util.List;

/**
 * 图G是联通的，即任何一对顶点之间至少有一条路径
 * @author wangheng
 *
 */
public interface ConnectedGraph {

	Tree getTree();
	/**
	 * 基本回路数
	 * @return
	 */
	List<Loop> basicLoops();
	
	
}
