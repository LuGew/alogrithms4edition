package com.lugew.alogrithms4edition.graphs.shortestPaths;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 基于队列的Bellman-Ford算法
 * Bellman-Ford算法：在任意含有V个顶点的加权有向图中给定起点s，从s无法到达任何负权重环，一下算法能够解决其中的单点最短路径问题：
 * 将distTo[s]初始化为零，其他distTo[]初始化为无穷大。以任意顺序放松有向图的所有便，重复V轮。
 * 时间复杂度：EV
 * 空间复杂度：V
 *
 * @author LuGew
 * @since 2018/5/2
 */
public class BellmanFordShortestPahts {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordShortestPahts(EdgeWeightedDigraph edgeWeightedDigraph, int source) {
        distTo = new double[edgeWeightedDigraph.getVertexes()];
        edgeTo = new DirectedEdge[edgeWeightedDigraph.getVertexes()];
        onQ = new boolean[edgeWeightedDigraph.getVertexes()];
        queue = new LinkedList<>();
        for (int i = 0; i < edgeWeightedDigraph.getVertexes(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;
        queue.offer(source);
        onQ[source] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.remove();
            onQ[v] = true;

        }
//          通用Bellman-Ford算法
//        for (int i = 0; i < edgeWeightedDigraph.getVertexes(); i++) {
//            for (int j = 0; j < edgeWeightedDigraph.getVertexes(); j++) {
//                relax(edgeWeightedDigraph, j);
//            }
//        }
    }

    public double distanceTo(int vertex) {
        return distTo[vertex];
    }

    public boolean hasPathTo(int vertex) {
        if (distTo[vertex] != Double.NEGATIVE_INFINITY) {
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

    private void relax(EdgeWeightedDigraph edgeWeightedDigraph, int vertex) {
        for (DirectedEdge de :
                edgeWeightedDigraph.getAdjacencyVertexes(vertex)) {
            int to = de.to();
            if (distTo[to] > distTo[vertex] + de.weight()) {
                edgeTo[to] = de;
                distTo[to] = distTo[vertex] + de.weight();
                if (!onQ[to]) {
                    queue.offer(to);
                    onQ[to] = true;
                }
            }
            if (cost++ % edgeWeightedDigraph.getVertexes() == 0) {
                findNegativeCycle();
            }
        }
    }

    private void findNegativeCycle() {
        int v = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(v);
        for (int i = 0; i < v; i++) {
            if (edgeTo[i] != null) {
                spt.addEdge(edgeTo[i]);
            }
        }

    }

    public boolean hasNegativeCycle() {
        return false;
    }

    public Iterable<DirectedEdge> negativeCycle() {
        return null;
    }
}
