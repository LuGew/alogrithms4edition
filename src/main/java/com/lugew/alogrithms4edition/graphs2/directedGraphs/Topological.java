package com.lugew.alogrithms4edition.graphs2.directedGraphs;

import graphs2.ShortestPaths.EdgeWeightedDigraph;

/**
 * @author LuGew
 * 有向图的拓扑排序
 * @since 2018/7/25
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph digraph) {
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (!directedCycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
            order = depthFirstOrder.reversePost();
        }
    }

    public Topological(EdgeWeightedDigraph edgeWeightedDigraph) {
        DirectedCycle directedCycle = new DirectedCycle(edgeWeightedDigraph);
        if (!directedCycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(edgeWeightedDigraph);
            order = depthFirstOrder.reversePost();
        }
    }

    /**
     * 有向无环图
     *
     * @return 是否
     */
    public boolean isDAG() {
        return order == null;
    }

    /**
     * 拓扑排序
     *
     * @return 拓扑排序顶点
     */
    public Iterable<Integer> order() {
        return order;
    }
}
