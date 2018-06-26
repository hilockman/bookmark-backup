package com.study.graph;
/**
 * 支路，亦成为边（Edge），一条支路有两个端点，即它与两个节点关联（不包括自回路Self-Loop)
 * @author wangheng
 *
 */
public interface Edge {

	/**
	 * 关联关系
	 * @author wangheng
	 *
	 */
	public static final class Incident
	{
		public int first;
		public int second;
		
		public Incident(int i, int j) {
			this.first = i;
			this.second = j;
		}
	}
	/**
	 * k(i,j),支路k与节点i，j关联
	 * @return
	 */
	Incident incident();
}
