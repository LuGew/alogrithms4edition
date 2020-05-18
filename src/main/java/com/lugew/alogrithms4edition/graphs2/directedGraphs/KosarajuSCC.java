package com.lugew.alogrithms4edition.graphs2.directedGraphs;

import java.util.Stack;

/**
 * @author LuGew
 * 有向图的强连通性
 * Kosaraju算法
 * @since 2018/7/25
 */
public class KosarajuSCC {
    private boolean marked[];//访问标志
    private int[] id;// 连通分量标志
    private int count;// 连通分量数

    public KosarajuSCC(Digraph digraph) {
        marked = new boolean[digraph.getVertexes()];
        id = new int[digraph.getVertexes()];
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph.reverse());
        for (Integer vertex :
                depthFirstOrder.reversePost()) {
            if (!marked[vertex]) {
                depthFirstSearchByRecursion(digraph, vertex);
                count++;
            }
        }
    }

    /**
     * 深度优先搜索
     * 递归实现
     *
     * @param digraph 图
     * @param vertex  顶点
     */
    private void depthFirstSearchByRecursion(Digraph digraph, int vertex) {
        marked[vertex] = true;
        id[vertex] = count;
        for (Integer v :
                digraph.adjacentVertexes(vertex)) {
            if (!marked[v]) {
                depthFirstSearchByRecursion(digraph, v);
            }
        }
    }

    /**
     * 深度优先搜索
     * 非递归实现
     *
     * @param digraph 图
     * @param vertex  顶点
     */
    private void depthFirstSearch(Digraph digraph, int vertex) {
        Stack<Integer> stack = new Stack<>();
        marked[vertex] = true;
        while (!stack.isEmpty()) {
            int v = getAdjacentVertex(digraph, stack.peek());
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
     * @param digraph 图
     * @param vertex  顶点
     * @return 邻接点
     */
    private int getAdjacentVertex(Digraph digraph, int vertex) {
        for (Integer v :
                digraph.adjacentVertexes(vertex)) {
            if (!marked[v]) {
                return v;
            }
        }
        return -1;
    }

    /**
     * 两个顶点的强连通性
     *
     * @param v 顶点
     * @param w 顶点
     * @return 强连通
     */
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int vertex) {
        return id[vertex];
    }
}
