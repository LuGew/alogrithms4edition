package com.lugew.alogrithms4edition.graphs2.minimumSpanningTrees;

import sorting.priorityQueues.IndexMinimumPriorityQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * @author LuGew
 * 即时Prim算法的实现
 * @since 2018/7/26
 */
public class PrimMST {
    private Edge[] edgeTo;//记录当前生成树的边
    private double[] distTo;//记录当前边的最短距离
    private boolean[] marked;//访问标志
    private IndexMinimumPriorityQueue<Double> pq;//索引优先队列

    /**
     * 构造函数
     *
     * @param edgeWeightedGraph 有权无向图
     */
    public PrimMST(EdgeWeightedGraph edgeWeightedGraph) {
        edgeTo = new Edge[edgeWeightedGraph.vertexes()];
        distTo = new double[edgeWeightedGraph.vertexes()];
        marked = new boolean[edgeWeightedGraph.vertexes()];
        for (int i = 0; i < edgeWeightedGraph.vertexes(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinimumPriorityQueue<>(edgeWeightedGraph.vertexes());
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            visit(edgeWeightedGraph, pq.deleteMinimum());
        }
    }

    /**
     * 遍历当前顶点的边，加入索引优先队列中
     *
     * @param edgeWeightedGraph 有权无向图
     * @param vertex            顶点
     */
    private void visit(EdgeWeightedGraph edgeWeightedGraph, int vertex) {
        marked[vertex] = true;
        for (Edge edge :
                edgeWeightedGraph.adjacentEdges(vertex)) {
            int w = edge.other(vertex);
            if (marked[w]) {
                continue;
            }
            if (edge.weight() < distTo[w]) {
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
                if (pq.contains(w)) {
                    pq.change(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    /**
     * 最小生成树的边
     *
     * @return 边
     */
    public Iterable<Edge> edges() {
        List<Edge> edges = new LinkedList<>();
        for (int i = 1; i < edgeTo.length; i++) {
            edges.add(edgeTo[i]);
        }
        return edges;
    }

    /**
     * 最小生成树的权重
     *
     * @return 权重
     */
    public double weight() {
        double weight = 0.0;
        for (int i = 0; i < distTo.length; i++) {
            weight += distTo[i];
        }
        return weight;
    }
}
