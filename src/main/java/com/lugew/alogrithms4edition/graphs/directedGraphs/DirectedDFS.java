package com.lugew.alogrithms4edition.graphs.directedGraphs;

import java.util.Iterator;
import java.util.Stack;

/**
 * 有向无权图连通性
 * 1.java垃圾回收机制（多点可达性）
 * 2.
 * @author LuGew
 * @since 2018/4/23
 */
public class DirectedDFS {
    //访问标志
    private boolean marked[];

    /**
     * 单点连通
     *
     * @param digraph 有向无权图
     * @param source  起点
     */
    public DirectedDFS(Digraph digraph, int source) {
        marked = new boolean[digraph.getVertexes()];
        depthFirstSearch(digraph, source);
    }

    /**
     * 多点连通性
     *
     * @param digraph 有向无权图
     * @param sources 起点数组
     */
    public DirectedDFS(Digraph digraph, Iterator<Integer> sources) {
        marked = new boolean[digraph.getVertexes()];
        int current;
        while (sources.hasNext()) {
            current = sources.next();
            if (!marked[current]) {
                depthFirstSearch(digraph, current);
            }
        }
    }

    /**
     * 深度优先搜索
     *
     * @param digraph 有向无权图
     * @param vertex  顶点
     */
    public void depthFirstSearch(Digraph digraph, int vertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(vertex);
        marked[vertex] = true;
        int current;
        while (!stack.isEmpty()) {
            current = getNext(digraph, vertex);
            if (current == -1) {
                stack.pop();
            } else {
                marked[current] = true;
                stack.push(current);
            }
        }
    }

    /**
     * 顶点连通性
     *
     * @param vertex 顶点
     * @return true：是；false：否
     */
    public boolean marked(int vertex) {
        return marked[vertex];
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
