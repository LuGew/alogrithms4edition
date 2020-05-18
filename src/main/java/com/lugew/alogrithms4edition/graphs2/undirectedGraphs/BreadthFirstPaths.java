package com.lugew.alogrithms4edition.graphs2.undirectedGraphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author LuGew
 * 最短路径
 * @since 2018/7/24
 */
public class BreadthFirstPaths {
    private boolean marked[];//访问标志
    private int[] edgeTo;// 边标志
    private final int source;// 起点

    public BreadthFirstPaths(Graph graph, int source) {
        marked = new boolean[graph.getVertexes()];
        edgeTo = new int[graph.getVertexes()];
        this.source = source;
        breadthFirstSearch(graph, source);
//        depthFirstSearch(graph,source);
    }

    private void breadthFirstSearch(Graph graph, int source) {
        Queue<Integer> queue = new LinkedList<>();
        marked[source] = true;
        while (!queue.isEmpty()) {
            Integer vertex = queue.remove();
            for (Integer v :
                    graph.adjacentVertexes(vertex)) {
                if (!marked[v]) {
                    edgeTo[v] = vertex;
                    marked[v] = true;
                    queue.add(v);
                }
            }
        }
    }


    /**
     * 含有路径
     *
     * @param destination 终点
     * @return 是否含有路径
     */
    public boolean hasPathTo(int destination) {
        return marked[destination];
    }

    /**
     * 顶点路径
     *
     * @param vertex 顶点
     * @return 路径
     */
    public Iterable<Integer> pathTo(int vertex) {
        if (!hasPathTo(vertex)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int i = vertex; i != source; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(source);
        return path;
    }
}
