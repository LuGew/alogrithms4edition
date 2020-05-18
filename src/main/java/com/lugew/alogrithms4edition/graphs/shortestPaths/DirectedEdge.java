package com.lugew.alogrithms4edition.graphs.shortestPaths;

/**
 * 有权有向图边
 *
 * @author LuGew
 * @since 2018/5/1
 */
public class DirectedEdge {
    private int from;
    private int to;
    private double weight;

    public DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    @Override
    public String toString() {
        return "DirectedEdge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }
}
