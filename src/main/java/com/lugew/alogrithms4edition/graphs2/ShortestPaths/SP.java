package com.lugew.alogrithms4edition.graphs2.ShortestPaths;

import java.util.Stack;

/**
 * @author LuGew
 * 最短路径
 * @since 2018/7/27
 */
public class SP {

    private double distTo[];
    private DirectedEdge edgeTo[];

    public SP(EdgeWeightedDigraph edgeWeightedDigraph, int source) {
        distTo = new double[edgeWeightedDigraph.getVertexes()];
        edgeTo = new DirectedEdge[edgeWeightedDigraph.getVertexes()];
        distTo[source] = 0;
        edgeTo[source] = null;
    }

    public double distTo(int vertex) {
        return distTo[vertex];
    }

    public boolean hasPathTo(int vertex) {
        return distTo[vertex] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int vertex) {
        if (hasPathTo(vertex)) {
            return null;
        }
        Stack<DirectedEdge> shortestPath = new Stack<>();
        for (DirectedEdge directedEdge = edgeTo[vertex]; directedEdge != null; directedEdge = edgeTo[directedEdge.from()]) {
            shortestPath.push(directedEdge);
        }
        return shortestPath;
    }

    private void relax(EdgeWeightedDigraph edgeWeightedDigraph, int vertex) {
        for (DirectedEdge directedEdge :
                edgeWeightedDigraph.adjacentDirectedEdges(vertex)) {
            int w = directedEdge.to();
            if (distTo[w] > distTo[vertex] + directedEdge.weight()) {
                distTo[w] = distTo[vertex] + directedEdge.weight();
                edgeTo[w] = directedEdge;
            }
        }
    }
}
