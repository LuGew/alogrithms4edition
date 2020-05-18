package com.lugew.alogrithms4edition.graphs2.minimumSpanningTrees;

/**
 * @author LuGew
 * 加权无向图的边
 * @since 2018/7/26
 */
public class Edge implements Comparable<Edge> {
    private int v;//顶点
    private int w;//顶点
    private double weight;//权重

    /**
     * 构造函数
     *
     * @param v      顶点
     * @param w      顶点
     * @param weight 权重
     */
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 权重
     *
     * @return 权重
     */
    public double weight() {
        return weight;
    }

    /**
     * 任意一个顶点
     *
     * @return 顶点
     */
    public int either() {
        return v;
    }

    /**
     * 顶点的边相连的顶点
     *
     * @param vertex 顶点
     * @return 顶点
     */
    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new RuntimeException("Inconsistent edge");
        }
    }


    @Override
    public int compareTo(Edge that) {
        if (this.weight() < that.weight()) {
            return -1;
        } else if (this.weight() > that.weight()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
