package com.lugew.alogrithms4edition.graphs2.ShortestPaths;

import graphs2.directedGraphs.Topological;
import sorting.priorityQueues.IndexMinimumPriorityQueue;

/**
 * @author LuGew
 * 无环加权图最短路径
 * @since 2018/7/27
 */
public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph edgeWeightedDigraph, int source) {
        edgeTo = new DirectedEdge[edgeWeightedDigraph.getVertexes()];
        distTo = new double[edgeWeightedDigraph.getVertexes()];
        for (int i = 0; i < edgeWeightedDigraph.getVertexes(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0;
        Topological topological = new Topological(edgeWeightedDigraph);
        for (int v :
                topological.order()) {
            relax(edgeWeightedDigraph, v);
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
            }
        }
    }
}
