package com.lugew.alogrithms4edition.practice.undirectedGraphs;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 * @since 2018/5/7
 */
public class Graph {
    private int vertexes;
    private int edges;
    private List<Integer> adjacencyList[];

    public Graph(int vertexes) {
        this.vertexes = vertexes;
        this.edges = 0;
        adjacencyList = new LinkedList[vertexes];
        for (int i = 0; i < vertexes; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to) {
        adjacencyList[from].add(to);
        adjacencyList[to].add(from);
        edges++;
    }

    public int getVertexes() {
        return vertexes;
    }

    public int getEdges() {
        return edges;
    }

    public boolean isEmpty() {
        return edges == 0;
    }

    public Iterable<Integer> getAdjacencyVertexes(int vertex) {
        return adjacencyList[vertex];
    }
}
