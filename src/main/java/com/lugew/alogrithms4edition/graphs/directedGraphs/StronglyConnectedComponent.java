package com.lugew.alogrithms4edition.graphs.directedGraphs;

import java.util.Stack;

/**
 * 有向无权图的强连通分量
 * Kosaraju算法
 *
 * @author LuGew
 * @since 2018/4/24
 */
public class StronglyConnectedComponent {

    //强连通分量数
    private int count;
    //强连通分量标志
    private int ids[];
    //访问标志
    private boolean marked[];

    /**
     * 有向图的反转做深度优先搜索得到顶点的逆后序
     * 根据逆后序在原图中做深度优先搜索，每个顶点能访问到的顶点处在同一强连通分量中
     *
     * @param digraph 有权无向图
     */
    public StronglyConnectedComponent(Digraph digraph) {
        count = 0;
        ids = new int[digraph.getVertexes()];
        marked = new boolean[digraph.getVertexes()];
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph.reverse());
        Iterable<Integer> order = depthFirstOrder.getReversePost();
        for (int current :
                order) {
            if (!marked[current]) {
                depthFirstSearch(digraph, current);
                count++;
            }
        }

    }

    /**
     * 非递归的深度优先搜索
     *
     * @param digraph 有向无权图
     * @param vertex  顶点
     */
    private void depthFirstSearch(Digraph digraph, int vertex) {
        Stack<Integer> stack = new Stack<>();
        marked[vertex] = true;
        stack.push(vertex);
        while (!stack.isEmpty()) {
            int current = getNext(digraph, stack.peek());
            if (current == -1) {
                stack.pop();
            } else {
                marked[current] = true;
                ids[current] = count;
                stack.push(current);
            }
        }
    }


    /**
     * 两个顶点是否强连通
     *
     * @param from 起点
     * @param to   终点
     * @return true：是；false：否
     */
    public boolean stronglyConnected(int from, int to) {
        return ids[from] == ids[to];
    }

    /**
     * 强连通分量数
     *
     * @return 分量数
     */
    public int count() {
        return count;
    }

    /**
     * 顶点所在强连通分量标志
     *
     * @param vertex 顶点
     * @return 强连通分量标志
     */
    public int id(int vertex) {
        return ids[vertex];
    }

    /**
     * 顶点的为被标记的一个邻接顶点
     *
     * @param digraph 有向无权图
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
}
