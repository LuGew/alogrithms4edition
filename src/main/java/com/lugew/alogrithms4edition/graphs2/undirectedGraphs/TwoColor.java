package com.lugew.alogrithms4edition.graphs2.undirectedGraphs;

import java.util.Stack;

/**
 * @author LuGew
 * 检测二分图
 * @since 2018/7/24
 */
public class TwoColor {
    private boolean marked[];
    private boolean isTwoColorable = true;
    private boolean color[];

    public TwoColor(Graph graph) {
        color = new boolean[graph.getVertexes()];
        marked = new boolean[graph.getVertexes()];
        for (int i = 0; i < graph.getVertexes(); i++) {
            if (!marked[i]) {
                depthFirstSearchByRecursion(graph, i);
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
    private void depthFirstSearchByRecursion(Graph graph, int vertex) {
        marked[vertex] = true;
        for (Integer v :
                graph.adjacentVertexes(vertex)) {
            if (!marked[v]) {
                color[v] = !color[vertex];
                depthFirstSearchByRecursion(graph, v);
            } else if (color[v] == color[vertex]) {
                isTwoColorable = false;
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
                color[v] = color[vertex];
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
    private int getAdjacentVertex(Graph graph, int vertex) {
        for (Integer v :
                graph.adjacentVertexes(vertex)) {
            if (!marked[v]) {
                return v;
            } else if (color[v] == color[vertex]) {
                isTwoColorable = false;
            }
        }
        return -1;
    }
}
