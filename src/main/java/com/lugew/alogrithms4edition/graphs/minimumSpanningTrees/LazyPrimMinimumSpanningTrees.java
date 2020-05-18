package com.lugew.alogrithms4edition.graphs.minimumSpanningTrees;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 最小生成树延时Prim算法
 * 最小生成树森林
 *
 * @author LuGew
 * @since 2018/4/28
 */
public class LazyPrimMinimumSpanningTrees {
    private boolean marked[];
    private Queue<Edge> edgePriorityQueue;
    private List<Edge> edges;
    private double weight;

//    public LazyPrimMinimumSpanningTrees(EdgeWeightedGraph edgeWeightedGraph) {
//        marked = new boolean[edgeWeightedGraph.getVertexes()];
//        edgePriorityQueue = new PriorityQueue<>();
//        edges = new LinkedList<>();
//        weight = 0;
//        visit(edgeWeightedGraph, 0);
//        while (!edgePriorityQueue.isEmpty()) {
//            Edge edge = edgePriorityQueue.remove();
//            int v = edge.either();
//            int w = edge.other(v);
//            if (marked[v] && marked[w]) {
//                continue;
//            }
//            edges.add(edge);
//            weight += edge.getWeight();
//            if (!marked[v]) {
//                visit(edgeWeightedGraph, v);
//            }
//            if (!marked[w]) {
//                visit(edgeWeightedGraph, w);
//            }
//        }
//    }

    public LazyPrimMinimumSpanningTrees(EdgeWeightedGraph edgeWeightedGraph) {
        marked = new boolean[edgeWeightedGraph.getVertexes()];
        edgePriorityQueue = new PriorityQueue<>();
        edges = new LinkedList<>();
        weight = 0;

        for (int i = 0; i < edgeWeightedGraph.getVertexes(); i++) {
            if (marked[i]) {
                visit(edgeWeightedGraph, i);
                while (!edgePriorityQueue.isEmpty()) {
                    Edge edge = edgePriorityQueue.remove();
                    int v = edge.either();
                    int w = edge.other(v);
                    if (marked[v] && marked[w]) {
                        continue;
                    }
                    edges.add(edge);
                    weight += edge.getWeight();
                    if (!marked[v]) {
                        visit(edgeWeightedGraph, v);
                    }
                    if (!marked[w]) {
                        visit(edgeWeightedGraph, w);
                    }
                }
            }
        }

    }

    private void visit(EdgeWeightedGraph edgeWeightedGraph, int vertex) {
        marked[vertex] = true;
        for (Edge edge :
                edgeWeightedGraph.getAdjacencyVertexes(vertex)) {
            if (!marked[edge.other(vertex)]) {
                edgePriorityQueue.offer(edge);
            }
        }
    }

    public double getWeight() {
        return weight;
    }
}
