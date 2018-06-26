package com.study.graph;
/**
 * 
 * @author wangheng
 *  抽象支路和节点的集合，反应了所包含的各支路之间的联结关系，即节点与支路的关系
 */
public interface Graph {

	/**
	 * 节-支关联矩阵，
	 * 有向图G有N+1个节点，b条支路，其中l支路从节点i出发到节点j终止，则其(N+1)*b阶节点-支路关联矩阵如
	 * 节点\支路  1 ... l ... b
	 * 1   |          0           |
	 * ... |          ...         |
	 * i   |          1           |
	 * ... |          ...         | 
	 * j   |          -1          |
	 * ... |          ...         |
	 * N+1 |          0           |
	 * 其中的列矢量称为关联矢量
	 * 关联矩阵非常稀疏，每行非零元素等于该节点的度
	 * 降解节-支关联矩阵为删除了参考节点的关联矩阵，使得关联矩阵线性无关
	 * 支路安排上一般把树枝放前面，连枝集中在后面，如下形式
	 * 节点\支路  1 ... N N+1 ... b
	 * 1   |          |           |
	 * ... |          |           |
	 * i   |    AT    |      AL   |
	 * ... |  (N*N)   |    (N*L)  |   = [AT AL]
	 * j   |          |           |
	 * ... |          |           |
	 * N+1 |          |           |
	 *        树枝                  连枝
	 * @return
	 */
	Matrix nodeBrachIncidenceMatrix();
	
	/**
	 * 基本回路支路关联矩阵
	 * @return
	 */
	Matrix basicLoopBranchIncidenceMatrix(int [] treeEdges);
	
	/**
	 * 基本割集支路关联矩阵
	 * @return
	 */
	Matrix basicCutsetBrachIncidenceMatrix();
	
}
