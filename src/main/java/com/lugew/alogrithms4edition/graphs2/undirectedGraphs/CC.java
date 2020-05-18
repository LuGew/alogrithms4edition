package com.lugew.alogrithms4edition.graphs2.undirectedGraphs;

import java.util.Stack;

/**
 * @author LuGew
 * 连通分量
 * @since 2018/7/24
 */
public class CC {
    private boolean marked[];//访问标志
    private int id[];// 连通分量标志
    private int count;//连通分量数

    /**
     * 构造函数
     *
     * @param graph 图
     */
    public CC(Graph graph) {
        marked = new boolean[graph.getVertexes()];
        id = new int[graph.getVertexes()];
        for (int i = 0; i < graph.getVertexes(); i++) {
            if (!marked[i]) {
                count++;
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
        id[vertex] = count;
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
                id[v] = count;
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
     * 是否在同一连通分量中
     *
     * @param v 顶点
     * @param w 顶点
     * @return 是否连通
     */
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * 顶点连通分量标志
     *
     * @param vertex 顶点
     * @return 连通分量标志
     */
    public int id(int vertex) {
        return id[vertex];
    }

    /**
     * 连通分量数
     *
     * @return 连通分量数
     */
    public int count() {
        return count;
    }
}
