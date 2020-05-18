package com.lugew.alogrithms4edition.graphs.shortestPaths;

import graphs.directedGraphs.Topological;

import java.util.Stack;

/**
 * 无环加权图的最长路径算法
 * 最长路径是对优先级限制下的并行任务调度问题的解决办法，叫做关键路径
 * 找出关键路径的关键是构建由调度问题构建无环加权图
 * 1.创建一副无环加权图，包含一个起点source和终点destination，每个任务对应两个顶点（一个起始和一个终点）
 * 2.对于每个任务都有一条从它的起始顶点指向结束顶点的边，边的权重为任务所用的时间
 * 3.对于优先级显示v-->w,添加一条从v的结束顶点指向w的起始顶点的权重为零的边。
 * 4.为每个任务添加一条从起始顶点指向该任务的起始顶点的权重为零的边以及一条从该任务结束顶点只想结束顶点的权重为零的边。
 * 这样，每个顶点预计的开始时间即为从起点到它的起始顶点的最长距离
 * <p>
 * 此外，还有另一个问题
 * 相对于最后期限限制下的并行任务调度
 * 是一个加权有向图的最短路径问题（可能含有环和负权重的边）
 * 与上一个问题一样构造加权有向图，且为每条最后期限添加一条边：如果任务v必须在任务w启动后的d个时间内开始，则添加一条从v指向w的负权重
 * 为d的边。将所有边取反操作即可将该问题转化为一个最短路径问题，如果存在可行的调度方案，证明完成。判断一个调度方案可行也是计算的一部分
 * Dijkstra 算法只适用与正权值的边
 *
 * @author LuGew
 * @since 2018/5/2
 */
public class AcyclicLongestPaths {
    private DirectedEdge[] edgeTo;
    private double distanceTo[];

    public AcyclicLongestPaths(EdgeWeightedDigraph edgeWeightedDigraph, int source) {
        edgeTo = new DirectedEdge[edgeWeightedDigraph.getVertexes()];
        distanceTo = new double[edgeWeightedDigraph.getVertexes()];
        for (int i = 0; i < edgeWeightedDigraph.getVertexes(); i++) {
            distanceTo[i] = Double.NEGATIVE_INFINITY;
        }

        distanceTo[0] = 0.0;
        Topological topological = new Topological(edgeWeightedDigraph);

        for (int v : topological.order()) {
            relax(edgeWeightedDigraph, v);
        }
    }

    private void relax(EdgeWeightedDigraph edgeWeightedDigraph, int vertex) {
        for (DirectedEdge de :
                edgeWeightedDigraph.getAdjacencyVertexes(vertex)) {
            int to = de.to();
            if (distanceTo[to] < distanceTo[vertex] + de.weight()) {
                edgeTo[to] = de;
                distanceTo[to] = distanceTo[vertex] + de.weight();
            }
        }
    }

    public double distanceTo(int vertex) {
        return distanceTo[vertex];
    }

    public boolean hasPathTo(int vertex) {
        if (distanceTo[vertex] != Double.NEGATIVE_INFINITY) {
            return true;
        }
        return false;
    }

    public Iterable<DirectedEdge> pathTo(int vertex) {
        Stack<DirectedEdge> stack = new Stack<>();
        if (hasPathTo(vertex)) {
            for (DirectedEdge directedEdge = edgeTo[vertex]; directedEdge != null; directedEdge = edgeTo[directedEdge.from()]) {
                stack.push(directedEdge);
            }
        }
        return stack;
    }
}
