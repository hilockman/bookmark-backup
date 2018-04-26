package com.study.graph;

import java.util.List;

/**
 * 支路的最小集合，把图分成两个不连通的子图，其中一个子图可以是一个孤立节点
 * @author wangheng
 *
 */
public interface Cutset {

	List<Edge> branches();
}
