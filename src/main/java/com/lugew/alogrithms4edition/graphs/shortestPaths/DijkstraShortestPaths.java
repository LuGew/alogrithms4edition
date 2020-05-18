package com.lugew.alogrithms4edition.graphs.shortestPaths;

import sorting.priorityQueues.IndexMinimumPriorityQueue;

import java.util.Stack;

/**
 * Dijkstra最短路径算法
 *
 * @author LuGew
 * @since 2018/5/1
 */
public class DijkstraShortestPaths {
    private double distanceTo[];
    private DirectedEdge edgeTo[];
    private IndexMinimumPriorityQueue<Double> indexMinimum;

    public DijkstraShortestPaths(EdgeWeightedDigraph edgeWeightedDigraph, int source) {
        edgeTo = new DirectedEdge[edgeWeightedDigraph.getVertexes()];
        distanceTo = new double[edgeWeightedDigraph.getVertexes()];
        indexMinimum = new IndexMinimumPriorityQueue<>(edgeWeightedDigraph.getVertexes());
        for (int i = 0; i < edgeWeightedDigraph.getVertexes(); i++) {
            distanceTo[i] = Double.POSITIVE_INFINITY;
        }
        distanceTo[source] = 0.0;
        indexMinimum.insert(0, 0.0);
        while (!indexMinimum.isEmpty()) {
            int min = indexMinimum.deleteMinimum();
            relax(edgeWeightedDigraph, min);
        }
    }

    public double distanceTo(int vertex) {
        return distanceTo[vertex];
    }

    public boolean hasPathTo(int vertex) {
        if (distanceTo[vertex] != Double.POSITIVE_INFINITY) {
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
            if (distanceTo[to] > distanceTo[vertex] + de.weight()) {
                edgeTo[to] = de;
                distanceTo[to] = distanceTo[vertex] + de.weight();
            }

            if (indexMinimum.contains(to)) {
                indexMinimum.change(to, distanceTo[to]);
            } else {
                indexMinimum.insert(to, distanceTo[to]);
            }
        }
    }
}
