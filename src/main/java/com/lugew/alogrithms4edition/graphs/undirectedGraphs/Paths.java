package com.lugew.alogrithms4edition.graphs.undirectedGraphs;


import java.util.Iterator;
import java.util.Stack;

/**
 * 单点路径问题
 * 查询两点之间的路径
 *
 * @author LuGew
 * @since 2018/4/17
 */
public class Paths {
    //连通性标志 true：是；false：否
    private boolean marked[];

    private final int source;

    //和索引顶点相连的上一顶点集
    private int edgeTo[];

    private Graph graph;

    public Paths(Graph graph, int vertex) {
        marked = new boolean[graph.getVertexes()];
        edgeTo = new int[graph.getVertexes()];
        source = vertex;
        this.graph = graph;
        depthFirstSearch(graph, vertex);
//        depthTwo(graph, vertex);
    }

    /**
     * 非递归的深度优先搜索
     *
     * @param graph  无向无权图
     * @param vertex 顶点
     */
    private void depthFirstSearch(Graph graph, int vertex) {
        Stack<Integer> stack = new Stack<>();
        marked[vertex] = true;
        stack.push(vertex);
        int temp;
        while (!stack.isEmpty()) {
            temp = stack.peek();
            int current = getNext(graph, temp);
            if (current == -1) {
                stack.pop();
            } else {
                marked[current] = true;
                edgeTo[current]
                        = temp;
                stack.push(current);
            }
        }
    }

    /**
     * 非递归的深度优先搜索
     *
     * @param graph  无向无权图
     * @param vertex 顶点
     */
    private void depthFirstSearch(Graph graph, int vertex, boolean marked2[]) {
        Stack<Integer> stack = new Stack<>();
        boolean marked[] = new boolean[marked2.length];
        marked[vertex] = true;
        stack.push(vertex);
        int temp;
        while (!stack.isEmpty()) {
            temp = stack.peek();
            int current = getNext(graph, temp, marked, marked2);
            if (current == -1) {
                stack.pop();
            } else {
                marked[current] = true;
                stack.push(current);
            }
        }
    }

    /**
     * 顶点的为被标记的一个邻接顶点
     *
     * @param graph  无向无权图
     * @param vertex 顶点
     * @return -1:不存在；
     */
    private int getNext(Graph graph, int vertex) {
        for (int current :
                graph.getAdjacencyVertexes(vertex)) {
            if (!marked[current]) {
                return current;
            }
        }
        return -1;
    }

    /**
     * 顶点的为被标记的一个邻接顶点
     *
     * @param graph  无向无权图
     * @param vertex 顶点
     * @return -1:不存在；
     */
    private int getNext(Graph graph, int vertex, boolean marked[], boolean marked2[]) {
        for (int current :
                graph.getAdjacencyVertexes(vertex)) {
            if (!marked[current] && !marked2[current]) {
                return current;
            }
        }
        return -1;
    }

    /**
     * 是否含有一条通往顶点的路径
     *
     * @param vertex 顶点
     * @return true：含；false：不含
     */
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    /**
     * 从起点到顶点的路径
     *
     * @param vertex 终点
     * @return 路径上的顶点集合
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

    /**
     * 边连通性
     *
     * @param vertex 终点
     * @return 顶点对是否是边连通性
     */
    public boolean edgeConnected(int vertex) {
        if (hasPathTo(vertex)) {
            Iterator<Integer> current = pathTo(vertex);
            boolean marked2[] = new boolean[this.marked.length];
            while (current.hasNext()) {
                marked[current.next()] = true;
            }
            depthFirstSearch(graph, vertex, marked2);

        }
        return false;
    }
}
