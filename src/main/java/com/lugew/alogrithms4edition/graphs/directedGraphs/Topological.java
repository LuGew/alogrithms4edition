package com.lugew.alogrithms4edition.graphs.directedGraphs;

import graphs.shortestPaths.EdgeWeightedDigraph;

import java.util.Queue;
import java.util.Stack;

/**
 * 有向无权图的拓扑排序
 * 拓扑排序即为一副有向无环图的深度优先搜索顶点的逆后序
 *
 * @author LuGew
 * @since 2018/4/24
 */
public class Topological {


    //顶点序列
    private Iterable<Integer> order;
    private Digraph digraph;
    private EdgeWeightedDigraph edgeWeightedDigraph;

    /**
     * 拓扑排序初始化
     * 第一遍检查是否含有环，第二遍得到深度优先搜索的逆后序
     *
     * @param digraph 有向无权图
     */
    public Topological(Digraph digraph) {
        this.digraph = digraph;
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (!directedCycle.isHasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
            order = depthFirstOrder.getReversePost();
        }
    }

    public Topological(EdgeWeightedDigraph edgeWeightedDigraph) {
        this.edgeWeightedDigraph = edgeWeightedDigraph;
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (!directedCycle.isHasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
            order = depthFirstOrder.getReversePost();
        }
    }


    /**
     * 是否为有向无权图
     *
     * @return true：是；false：否
     */
    public boolean isDirectedAcyclicGraph() {
        return order != null;
    }

    /**
     * 拓扑排序
     *
     * @return 拓扑排序序列
     */
    public Iterable<Integer> order() {
        return order;
    }

}
