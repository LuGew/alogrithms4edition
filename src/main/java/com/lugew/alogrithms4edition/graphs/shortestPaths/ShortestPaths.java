package com.lugew.alogrithms4edition.graphs.shortestPaths;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 有权有向图的最短路径
 *
 * @author LuGew
 * @since 2018/5/1
 */
public class ShortestPaths {
    private int source;
    private boolean marked[];
    private Queue<DirectedEdge> shorestPaths;
    private double distanceTo[];
    private DirectedEdge edgeTo[];

    public ShortestPaths(EdgeWeightedDigraph edgeWeightedDigraph, int source) {
        this.source = source;
        marked = new boolean[edgeWeightedDigraph.getVertexes()];
        shorestPaths = new LinkedList<>();

    }

    public void shortPaths(EdgeWeightedDigraph edgeWeightedDigraph, int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        marked[vertex] = true;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            for (DirectedEdge e :
                    edgeWeightedDigraph.getAdjacencyVertexes(current)) {
                if (!marked[e.to()]) {
                    queue.offer(e.to());
                }
            }
        }
    }

    public double distTo(int vertex) {
        return distanceTo[vertex];
    }

    public boolean hasPathTo(int vertex) {
        return distanceTo[vertex] == Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> patpTo(int vertex) {
        if (!hasPathTo(vertex)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge directedEdge = edgeTo[vertex]; directedEdge != null; directedEdge = edgeTo[directedEdge.from()]) {
            path.push(directedEdge);
        }

        return path;
    }
}


