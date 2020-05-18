package com.lugew.alogrithms4edition.graphs2.undirectedGraphs;

import java.util.Stack;

/**
 * @author LuGew
 * 检测环
 * @since 2018/7/24
 */
public class Cycle {
    private boolean marked[];
    private boolean hasCycle;

    public Cycle(Graph graph) {
        marked = new boolean[graph.getVertexes()];
        for (int i = 0; i < graph.getVertexes(); i++) {
            if (!marked[i]) {
                depthFirstSearchByRecursion(graph, i, i);
            }
        }
    }

    /**
     * 深度优先搜索
     * 递归实现
     *
     * @param graph  图
     * @param vertex 顶点
     */
    private void depthFirstSearchByRecursion(Graph graph, int vertex, int front) {
        marked[vertex] = true;
        for (Integer v :
                graph.adjacentVertexes(vertex)) {
            if (!marked[v]) {
                depthFirstSearchByRecursion(graph, v, vertex);
            } else if (v != front) {
                hasCycle = true;
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
    private void depthFirstSearch(Graph graph, int vertex, int front) {
        Stack<Integer> stack = new Stack<>();
        marked[vertex] = true;
        while (!stack.isEmpty()) {
            int v = getAdjacentVertex(graph, stack.peek(), front);
            if (v == -1) {
                stack.pop();
            } else {
                front = v;
                marked[v] = true;
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
    private int getAdjacentVertex(Graph graph, int vertex, int front) {
        for (Integer v :
                graph.adjacentVertexes(vertex)) {
            if (!marked[v]) {
                return v;
            } else {
                if (v != front) {
                    hasCycle = true;
                }
            }
        }
        return -1;
    }
}
