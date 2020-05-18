package com.lugew.alogrithms4edition.graphs.minimumSpanningTrees;

/**
 * 有权无向图边
 *
 * @author LuGew
 * @since 2018/4/28
 */
public class Edge implements Comparable<Edge> {
    private int from;
    private int to;
    private double weight;

    public Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    public int either() {
        return from;
    }

    public int other(int vertex) {
        return vertex == from ? to : from;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight > o.getWeight()) {
            return 1;
        } else if (this.weight < o.getWeight()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }
}
