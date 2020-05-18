package com.lugew.alogrithms4edition.graphs.directedGraphs;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 广度优先搜索
 * 寻找无权无向图中单点最短路径
 *
 * @author lugew
 * @since 2018/4/18
 */
public class BreadthFirstDirectedPaths {
    private boolean marked[];
    private int edgeTo[];

    private final int source;

    public BreadthFirstDirectedPaths(Digraph graph, int source) {
        this.source = source;
        this.edgeTo = new int[graph.getVertexes()];
        this.marked = new boolean[graph.getVertexes()];
        breadthFirstSearch(graph, source);
    }


    /**
     * 非递归的广度优先搜索
     *
     * @param graph  图
     * @param vertex 起始顶点
     */
    public void breadthFirstSearch(Digraph graph, int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        marked[vertex] = true;
        queue.offer(vertex);
        int temp;
        while (!queue.isEmpty()) {
            temp = queue.remove();
            for (int current :
                    graph.getAdjacencyVertexes(temp)) {
                if (!marked[current]) {
                    marked[current] = true;
                    edgeTo[current] = temp;
                    queue.offer(current);
                }
            }
        }
    }

    /**
     * 起点和终点是否存在一条路径
     *
     * @param vertex 终点
     * @return true：存在；false：不
     */
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    /**
     * 起点到终点的路径
     *
     * @param vertex 终点
     * @return 路径迭代器
     */
    public Iterator<Integer> pathTo(int vertex) {
        if (hasPathTo(vertex)) {
            Stack<Integer> stack = new Stack<>();
            while ((vertex = edgeTo[vertex]) != source) {
                stack.push(vertex);
            }
            return stack.iterator();
        }
        return null;
    }


}
