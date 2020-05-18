package com.lugew.alogrithms4edition.graphs2.undirectedGraphs;

import java.util.Stack;

/**
 * @author LuGew
 * 连通性-->路径
 * @since 2018/7/24
 */
public class Paths {
    private boolean marked[];
    private int[] edgeTo;
    private final int source;

    public Paths(Graph graph, int source) {
        marked = new boolean[graph.getVertexes()];
        edgeTo = new int[graph.getVertexes()];
        this.source = source;
        depthFirstSearchByRecursion(graph, source);
//        depthFirstSearch(graph,source);
    }

    /**
     * 深度优先搜索
     * 递归实现
     *
     * @param graph  图
     * @param vertex 顶点
     */
    private void depthFirstSearchByRecursion(Graph graph, int vertex) {
        marked[vertex] = true;
        for (Integer v :
                graph.adjacentVertexes(vertex)) {
            if (!marked[v]) {
                edgeTo[v] = vertex;
                depthFirstSearchByRecursion(graph, v);
            }
        }
    }

    /**
     * 深度优先搜索
     * 非递归实现
     *
     * @param graph  图
     * @param vertex 顶点
     */
    private void depthFirstSearch(Graph graph, int vertex) {
        Stack<Integer> stack = new Stack<>();
        marked[vertex] = true;
        while (!stack.isEmpty()) {
            int v = getAdjacentVertex(graph, stack.peek());
            if (v == -1) {
                stack.pop();
            } else {
                marked[v] = true;
                edgeTo[v] = stack.peek();
                stack.push(v);
            }
        }
    }

    /**
     * 获取顶点未被访问的邻接点
     *
     * @param graph  图
     * @param vertex 顶点
     * @return 邻接点
     */
    private int getAdjacentVertex(Graph graph, int vertex) {
        for (Integer v :
                graph.adjacentVertexes(vertex)) {
            if (!marked[v]) {
                return v;
            }
        }
        return -1;
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
