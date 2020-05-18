package com.lugew.alogrithms4edition.graphs.directedGraphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 深度优先搜索顶点序列
 *
 * @author LuGew
 * @since 2018/4/24
 */
public class DepthFirstOrder {
    private boolean marked[];
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph digraph) {
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();
        marked = new boolean[digraph.getVertexes()];
        for (int i = 0; i < digraph.getVertexes(); i++) {
            if (!marked[i]) {
                depthFirstSearch(digraph, i);
            }
        }
    }

    /**
     * 深度优先搜索
     *
     * @param digraph 有向无权图
     * @param vertex  顶点
     */
    private void depthFirstSearch(Digraph digraph, int vertex) {
        Stack<Integer> stack = new Stack<>();
        marked[vertex] = true;
        pre.offer(vertex);
        stack.push(vertex);
        while (!stack.isEmpty()) {
            int current = getNext(digraph, vertex);
            if (current == -1) {
                post.offer(current);
                reversePost.push(current);
                stack.pop();
            } else {
                marked[current] = true;
                pre.offer(current);
                stack.push(current);
            }
        }
    }

    /**
     * 顶点的为被标记的一个邻接顶点
     *
     * @param digraph 无向无权图
     * @param vertex  顶点
     * @return -1:不存在；
     */
    private int getNext(Digraph digraph, int vertex) {
        for (int current :
                digraph.getAdjacencyVertexes(vertex)) {
            if (!marked[current]) {
                return current;
            }
        }
        return -1;
    }

    public Iterable<Integer> getPre() {
        return pre;
    }

    public Iterable<Integer> getPost() {
        return post;
    }

    public Iterable<Integer> getReversePost() {
        return reversePost;
    }
}
