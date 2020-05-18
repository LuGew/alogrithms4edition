package com.lugew.alogrithms4edition.graphs.shortestPaths;

import graphs.directedGraphs.Topological;

import java.util.Stack;

/**
 * 无环加权图的最短路径算法
 *
 * @author LuGew
 * @since 2018/5/2
 */
public class AcyclicShortestPaths {
    private DirectedEdge[] edgeTo;
    private double distanceTo[];

    public AcyclicShortestPaths(EdgeWeightedDigraph edgeWeightedDigraph, int source) {
        edgeTo = new DirectedEdge[edgeWeightedDigraph.getVertexes()];
        distanceTo = new double[edgeWeightedDigraph.getVertexes()];
        for (int i = 0; i < edgeWeightedDigraph.getVertexes(); i++) {
            distanceTo[i] = Double.POSITIVE_INFINITY;
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
            if (distanceTo[to] > distanceTo[vertex] + de.weight()) {
                edgeTo[to] = de;
                distanceTo[to] = distanceTo[vertex] + de.weight();
            }
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
}
