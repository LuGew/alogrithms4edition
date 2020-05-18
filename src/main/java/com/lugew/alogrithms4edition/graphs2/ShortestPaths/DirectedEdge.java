package com.lugew.alogrithms4edition.graphs2.ShortestPaths;

/**
 * @author LuGew
 * 加权有向图的边
 * @since 2018/7/26
 */
public class DirectedEdge implements Comparable<DirectedEdge> {
    private int from;//顶点
    private int to;//顶点
    private double weight;//权重

    /**
     * 构造函数
     *
     * @param from   顶点
     * @param to     顶点
     * @param weight 权重
     */
    public DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
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
     * 终点
     *
     * @return 终点
     */
    public int to() {
        return to;
    }

    /**
     * 起点
     *
     * @return 起点
     */
    public int from() {
        return from;
    }


    @Override
    public int compareTo(DirectedEdge that) {
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
        return String.format("%d-%d %.2f", from, to, weight);
    }
}
