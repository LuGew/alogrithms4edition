package com.lugew.alogrithms4edition.graphs2.minimumSpanningTrees;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author LuGew
 * 延时Prim最小生成树算法
 * @since 2018/7/26
 */
public class LazyPrimMST {
    private boolean[] marked;//访问标志
    private Queue<Edge> mst;//最小生成树
    private PriorityQueue<Edge> pq;//优先队列

    /**
     * 构造函数
     *
     * @param edgeWeightedGraph 有权无向图
     */
    public LazyPrimMST(EdgeWeightedGraph edgeWeightedGraph) {
        pq = new PriorityQueue<>();
        marked = new boolean[edgeWeightedGraph.vertexes()];
        mst = new LinkedList<>();
        visit(edgeWeightedGraph, 0);
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int v = edge.either();
            int w = edge.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.add(edge);
            if (!marked[v]) {
                visit(edgeWeightedGraph, v);
            }
            if (!marked[w]) {
                visit(edgeWeightedGraph, w);
            }
        }
    }


    /**
     * 广度优先搜索
     *
     * @param edgeWeightedGraph 图
     * @param vertex            顶点
     */
    private void visit(EdgeWeightedGraph edgeWeightedGraph, int vertex) {
        marked[vertex] = true;
        for (Edge edge :
                edgeWeightedGraph.adjacentEdges(vertex)) {
            if (!marked[edge.other(vertex)]) {
                pq.offer(edge);
            }
        }
    }

    /**
     * 最小生成树
     *
     * @return 最小生成树
     */
    public Iterable<Edge> mst() {
        return mst;
    }

    /**
     * 最小生成树的权重
     *
     * @return 权重
     */
    public double weight() {
        double weight = 0;
        for (Edge edge :
                mst) {
            weight += edge.weight();
        }
        return weight;
    }
}
