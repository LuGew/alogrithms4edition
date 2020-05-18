package com.lugew.alogrithms4edition.graphs.minimumSpanningTrees;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 即时Prim最小生成树算法
 *
 * @author LuGew
 * @since 2018/4/28
 */
public class PrimMinimumSpanningTrees {
    private Edge edgeTo[];
    private double distanceTo[];
    private Queue<Edge> edges;
    private double weight;

    public PrimMinimumSpanningTrees(EdgeWeightedGraph edgeWeightedGraph) {
        edgeTo = new Edge[edgeWeightedGraph.getVertexes()];
        distanceTo = new double[edgeWeightedGraph.getVertexes()];
        for (int i = 0; i < edgeWeightedGraph.getVertexes(); i++) {
            distanceTo[i] = Double.POSITIVE_INFINITY;
        }
        edges = new PriorityQueue<>();
        visit(edgeWeightedGraph, 0);
        while (!edges.isEmpty()) {
            Edge edge = edges.remove();
            weight += edge.getWeight();
            int x = edge.either();
            int y = edge.other(x);
            if (!(distanceTo[x] == Double.POSITIVE_INFINITY)) {
                distanceTo[x] = Double.POSITIVE_INFINITY;
                visit(edgeWeightedGraph, x);
            }

            if (!(distanceTo[y] == Double.POSITIVE_INFINITY)) {
                distanceTo[y] = Double.POSITIVE_INFINITY;
                visit(edgeWeightedGraph, y);
            }
        }
    }

    private void visit(EdgeWeightedGraph edgeWeightedGraph, int vertex) {
        for (Edge edge :
                edgeWeightedGraph.getAdjacencyVertexes(vertex)) {
            int other = edge.other(vertex);
            if (distanceTo[other] == Double.POSITIVE_INFINITY && edgeTo[other] == null && edge.getWeight() < distanceTo[other]) {
                distanceTo[other] = edge.getWeight();
                edgeTo[other] = edge;
                edges.offer(edge);
            }
        }
    }

    public Iterable<Edge> edges() {
        List<Edge> edges = new LinkedList<>();
        for (int i = 0; i < edgeTo.length; i++) {
            edges.add(edgeTo[i]);
        }
        return edges;
    }

    public double getWeight() {
        return weight;
    }
}
