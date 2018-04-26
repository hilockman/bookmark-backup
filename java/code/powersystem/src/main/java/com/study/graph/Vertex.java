package com.study.graph;

/**
 * 顶点，亦成为节点（Node），支路的连接
 * @author wangheng
 *
 */
public interface Vertex {

	/**
	 * 节点所关联的支路数
	 * @return
	 */
	int degree();
}
