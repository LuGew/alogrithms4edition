package com.lugew.alogrithms4edition.graphs2.minimumSpanningTrees;

import fundamentals.caseStudyUnionFind.UnionFind;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author LuGew
 * kruskal最小生成树算法
 * @since 2018/7/26
 */
public class KruskalMST {
    private Queue<Edge> mst;//最小生成树

    /**
     * 构造函数
     *
     * @param edgeWeightedGraph 有权无向图
     */
    public KruskalMST(EdgeWeightedGraph edgeWeightedGraph) {
        mst = new LinkedList<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        UnionFind unionFind = new UnionFind(edgeWeightedGraph.vertexes());
        while (!priorityQueue.isEmpty() && mst.size() < edgeWeightedGraph.vertexes() - 1) {
            Edge edge = priorityQueue.poll();
            int v = edge.either();
            int w = edge.other(v);
            if (unionFind.connected(v, w)) {
                continue;
            }
            unionFind.quickUnion(v, w);
            mst.offer(edge);
        }
    }

    /**
     * 最小生成树
     *
     * @return 最小生成树
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * 最小生成树权重
     *
     * @return 权重
     */
    public double weight() {
        double weight = 0.0;
        for (Edge edge :
                mst) {
            weight += edge.weight();
        }
        return weight;
    }
}
