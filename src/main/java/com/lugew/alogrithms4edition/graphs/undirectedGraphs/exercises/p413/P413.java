package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p413;

import graphs.undirectedGraphs.Graph;

/**
 * @author LuGew
 * @since 2018/4/19
 */
public class P413 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Graph graph = new Graph(2);
        Graph copy = graph.copyGraph();
        graph.addEdge(0, 1);
        System.out.println("graph edges:" + graph.getEdges());
        System.out.println("copy edges:" + copy.getEdges());
    }
}
