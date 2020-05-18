package com.lugew.alogrithms4edition.graphs2.undirectedGraphs;

/**
 * @author LuGew
 * @since 2018/7/24
 */
public class Search {
    private Graph graph;
    private boolean marked[];
    private int count;

    public Search(Graph graph, int source) {
        this.graph = graph;
        marked = new boolean[graph.getVertexes()];
    }

    public boolean marked(int vertex) {
        return marked[vertex];
    }

    public int count() {
        return count;
    }

}
