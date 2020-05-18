package com.lugew.alogrithms4edition.graphs2.ShortestPaths;

import sorting.priorityQueues.IndexMinimumPriorityQueue;

import java.util.Stack;

/**
 * @author LuGew
 * Dijkstra最短路径算法
 * 不能存在负权重边
 * @since 2018/7/27
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;//边
    private double[] distTo;//距离
    private IndexMinimumPriorityQueue<Double> indexMinimumPriorityQueue;//索引优先队列

    public DijkstraSP(EdgeWeightedDigraph edgeWeightedDigraph, int source) {
        edgeTo = new DirectedEdge[edgeWeightedDigraph.getVertexes()];
        distTo = new double[edgeWeightedDigraph.getVertexes()];
        indexMinimumPriorityQueue = new IndexMinimumPriorityQueue<>(edgeWeightedDigraph.getVertexes());
        for (int i = 0; i < edgeWeightedDigraph.getVertexes(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0;
        indexMinimumPriorityQueue.insert(source, 0.0);
        while (!indexMinimumPriorityQueue.isEmpty()) {
            relax(edgeWeightedDigraph, indexMinimumPriorityQueue.deleteMinimum());
        }
    }

    /**
     * 边的松弛
     *
     * @param edgeWeightedDigraph 有权有向图
     * @param vertex              顶点
     */
    private void relax(EdgeWeightedDigraph edgeWeightedDigraph, int vertex) {
        for (DirectedEdge directedEdge :
                edgeWeightedDigraph.adjacentDirectedEdges(vertex)) {
            int to = directedEdge.to();
            if (distTo[to] > distTo[directedEdge.from()] + directedEdge.weight()) {
                distTo[to] = distTo[directedEdge.from()] + directedEdge.weight();
                edgeTo[to] = directedEdge;
                if (indexMinimumPriorityQueue.contains(to)) {
                    indexMinimumPriorityQueue.change(to, distTo[to]);
                } else {
                    indexMinimumPriorityQueue.insert(to, distTo[to]);
                }
            }
        }
    }

    /**
     * 顶点到起点的距离
     *
     * @param vertex 顶点
     * @return 距离
     */
    public double distTo(int vertex) {
        return distTo[vertex];
    }

    /**
     * 是否含有路径
     *
     * @param vertex 顶点
     * @return 是否
     */
    public boolean hasPathTo(int vertex) {
        return distTo[vertex] < Double.POSITIVE_INFINITY;
    }

    /**
     * 最短路径
     *
     * @param vertex 顶点
     * @return 最短路径
     */
    public Iterable<DirectedEdge> pathTo(int vertex) {
        if (!hasPathTo(vertex)) {
            return null;
        }
        Stack<DirectedEdge> shortestPaht = new Stack<>();
        for (DirectedEdge directedEdge = edgeTo[vertex]; directedEdge != null; directedEdge = edgeTo[directedEdge.from()]) {
            shortestPaht.push(directedEdge);
        }
        return shortestPaht;
    }
}
