package com.lugew.alogrithms4edition.graphs2.directedGraphs;

import graphs2.ShortestPaths.DirectedEdge;
import graphs2.ShortestPaths.EdgeWeightedDigraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author LuGew
 * 有向图基于深度优先搜索的顶点排序
 * @since 2018/7/25
 */
public class DepthFirstOrder {
    private boolean marked[];//访问标志
    private Queue<Integer> pre;//前序
    private Queue<Integer> post;//后序
    private Stack<Integer> reversePost;//逆序

    /**
     * 构造函数
     *
     * @param digraph 有向图
     */
    public DepthFirstOrder(Digraph digraph) {
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();
        marked = new boolean[digraph.getVertexes()];
        for (int i = 0; i < digraph.getVertexes(); i++) {
            if (!marked[i]) {
                depthFirstSearchByRecursion(digraph, i);
            }
        }
    }

    /**
     * 构造函数
     *
     * @param edgeWeightedDigraph 有向图
     */
    public DepthFirstOrder(EdgeWeightedDigraph edgeWeightedDigraph) {
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();
        marked = new boolean[edgeWeightedDigraph.getVertexes()];
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
        pre.add(vertex);
        marked[vertex] = true;
        for (Integer v :
                digraph.adjacentVertexes(vertex)) {
            if (!marked[v]) {
                depthFirstSearchByRecursion(digraph, v);
            }
        }
        post.add(vertex);
        reversePost.add(vertex);
    }

    /**
     * 深度优先搜索
     * 递归实现
     *
     * @param edgeWeightedDigraph 图
     * @param vertex              顶点
     */
    private void depthFirstSearchByRecursion(EdgeWeightedDigraph edgeWeightedDigraph, int vertex) {
        pre.add(vertex);
        marked[vertex] = true;
        for (DirectedEdge directedEdge :
                edgeWeightedDigraph.adjacentDirectedEdges(vertex)) {
            int v = directedEdge.to();
            if (!marked[v]) {
                depthFirstSearchByRecursion(edgeWeightedDigraph, v);
            }
        }
        post.add(vertex);
        reversePost.add(vertex);
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
        pre.add(vertex);
        while (!stack.isEmpty()) {
            int v = getAdjacentVertex(digraph, stack.peek());
            if (v == -1) {
                post.add(stack.peek());
                reversePost.add(stack.peek());
                stack.pop();
            } else {
                pre.add(v);
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

    /**
     * 前序
     *
     * @return 顶点
     */
    public Queue<Integer> pre() {
        return pre;
    }


    /**
     * 后序
     *
     * @return 顶点
     */
    public Queue<Integer> post() {
        return post;
    }

    /**
     * 逆后序
     *
     * @return 顶点
     */
    public Stack<Integer> reversePost() {
        return reversePost;
    }

}
