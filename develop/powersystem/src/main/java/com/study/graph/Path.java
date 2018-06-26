package com.study.graph;
/**
 * 路径，即图G中从始点出发经过若干支路和节点到达终点，其中的支路和节点不能重复，形成的一个开边列.
 * 显然路径中的内部顶点度只能为2，开始和终点的度为1
 * @author wangheng
 *
 */
public interface Path {

	Vertex start();
	Vertex end();
	
}
