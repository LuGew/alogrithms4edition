package com.lugew.alogrithms4edition.graphs2.directedGraphs;

import graphs2.ShortestPaths.EdgeWeightedDigraph;
import graphs2.ShortestPaths.DirectedEdge;

import java.util.Stack;

/**
 * @author LuGew
 * 有向图环
 * @since 2018/7/25
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph digraph) {
        onStack = new boolean[digraph.getVertexes()];
        edgeTo = new int[digraph.getVertexes()];
        for (int i = 0; i < digraph.getVertexes(); i++) {
            if (!marked[i]) {
                depthFirstSearchByRecursion(digraph, i);
            }
        }
    }

    public DirectedCycle(EdgeWeightedDigraph edgeWeightedDigraph) {
        onStack = new boolean[edgeWeightedDigraph.getVertexes()];
        edgeTo = new int[edgeWeightedDigraph.getVertexes()];
        for (int i = 0; i < edgeWeightedDigraph.getVertexes(); i++) {
            if (!marked[i]) {
                depthFirstSearchByRecursion(edgeWeightedDigraph, i);
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
        onStack[vertex] = true;
        marked[vertex] = true;
        for (int v :
                digraph.adjacentVertexes(vertex)) {
            if (hasCycle()) {
                return;
            } else if (!marked[v]) {
                edgeTo[v] = vertex;
                depthFirstSearchByRecursion(digraph, v);
            } else if (onStack[v]) {
                cycle = new Stack<>();
                for (int x = vertex; x != v; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(v);
                cycle.push(vertex);
            }
        }
        onStack[vertex] = false;
    }

    /**
     * 深度优先搜索
     * 递归实现
     *
     * @param edgeWeightedDigraph 图
     * @param vertex              顶点
     */
    private void depthFirstSearchByRecursion(EdgeWeightedDigraph edgeWeightedDigraph, int vertex) {
        onStack[vertex] = true;
        marked[vertex] = true;
        for (DirectedEdge directedEdge :
                edgeWeightedDigraph.adjacentDirectedEdges(vertex)) {
            int v = directedEdge.to();
            if (hasCycle()) {
                return;
            } else if (!marked[v]) {
                edgeTo[v] = vertex;
                depthFirstSearchByRecursion(edgeWeightedDigraph, v);
            } else if (onStack[v]) {
                cycle = new Stack<>();
                for (int x = vertex; x != v; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(v);
                cycle.push(vertex);
            }
        }
        onStack[vertex] = false;
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
        onStack[vertex] = true;
        marked[vertex] = true;
        while (!stack.isEmpty()) {
            if (hasCycle()) {
                return;
            }
            int v = getAdjacentVertex(digraph, stack.peek());
            if (v == -1) {
                onStack[stack.peek()] = false;
                stack.pop();
            } else {
                marked[v] = true;
                edgeTo[v] = stack.peek();
                stack.push(v);
            }
        }
    }

    private int getAdjacentVertex(Digraph digraph, int vertex) {
        for (Integer v :
                digraph.adjacentVertexes(vertex)) {
            if (!marked[v]) {
                return v;
            } else if (onStack[v]) {
                cycle = new Stack<>();
                for (int x = vertex; x != v; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(v);
                cycle.push(vertex);
            }
        }
        return -1;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
