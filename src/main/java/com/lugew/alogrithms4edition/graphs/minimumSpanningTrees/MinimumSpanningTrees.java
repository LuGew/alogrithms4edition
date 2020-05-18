package com.lugew.alogrithms4edition.graphs.minimumSpanningTrees;

import java.util.List;

/**
 * 最小生成树
 *
 * @author LuGew
 * @since 2018/4/28
 */
public class MinimumSpanningTrees {
    private List<Edge> edges;
    private double weight;
    private boolean marked[];

    public MinimumSpanningTrees(EdgeWeightedGraph edgeWeightedGraph) {

    }

    public Iterable<Edge> edges() {
        return edges;
    }

    public double weight() {
        return weight;
    }
}
