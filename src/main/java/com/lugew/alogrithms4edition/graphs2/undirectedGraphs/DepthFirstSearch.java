package com.lugew.alogrithms4edition.graphs2.undirectedGraphs;

import java.util.Stack;

/**
 * @author LuGew
 * 深度优先搜索
 * @since 2018/7/24
 */
public class DepthFirstSearch {
    private boolean marked[];//访问标志
    private int count;//连通的顶点数

    /**
     * 构造函数
     *
     * @param graph  图
     * @param source 起点
     */
    public DepthFirstSearch(Graph graph, int source) {
        marked = new boolean[graph.getVertexes()];
        depthFirstSearchByRecursion(graph, source);
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
        count++;
        for (Integer v :
                graph.adjacentVertexes(vertex)) {
            if (!marked[v]) {
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
                stack.push(v);
            }
        }
    }

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
     * 顶点访问
     *
     * @param vertex 顶点
     * @return 访问标志
     */
    public boolean marked(int vertex) {
        return marked[vertex];
    }

    /**
     * 连通顶点数
     *
     * @return 顶点数
     */
    public int count() {
        return count;
    }
}
