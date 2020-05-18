package com.lugew.alogrithms4edition.graphs2.directedGraphs;

import java.util.Stack;

/**
 * @author LuGew
 * 有向图的可达性
 * 单点可达性
 * 多点可达性
 * 应用：java垃圾收集器
 * @since 2018/7/25
 */
public class DirectedDFS {
    private boolean[] marked;//访问标志

    /**
     * 单点可达性
     *
     * @param digraph 有向图
     * @param source  顶点
     */
    public DirectedDFS(Digraph digraph, int source) {
        marked = new boolean[digraph.getVertexes()];
        depthFirstSearchByRecursion(digraph, source);
//        depthFirstSearch(digraph, source);
    }

    /**
     * 多点可达性
     *
     * @param digraph 有向图
     * @param sources 顶点集
     */
    public DirectedDFS(Digraph digraph, Iterable<Integer> sources) {
        marked = new boolean[digraph.getVertexes()];
        for (Integer v :
                sources) {
            if (!marked[v]) {
                depthFirstSearchByRecursion(digraph, v);
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
                stack.push(v);
            }
        }
    }

    private int getAdjacentVertex(Digraph digraph, int vertex) {
        for (Integer v :
                digraph.adjacentVertexes(vertex)) {
            if (!marked[v]) {
                return v;
            }
        }
        return -1;
    }

    public boolean marked(int vertex) {
        return marked[vertex];
    }
}
