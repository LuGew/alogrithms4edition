package com.lugew.alogrithms4edition.graphs.directedGraphs;

import java.util.Iterator;
import java.util.Stack;

/**
 * 有向无权图的深度优先搜索连通路径
 *
 * @author LuGew
 * @since 2018/4/24
 */
public class DepthFirstDirectedPaths {
    //连通性标志 true：是；false：否
    private boolean marked[];
    //连通总数
    private int count;
    //起点
    private final int source;
    //和索引顶点相连的上一顶点集
    private int edgeTo[];

    /**
     * 构造以vertex为顶点的连通图
     *
     * @param digraph 有向无权图
     * @param vertex  顶点
     */
    public DepthFirstDirectedPaths(Digraph digraph, int vertex) {
        marked = new boolean[digraph.getVertexes()];
        edgeTo = new int[digraph.getVertexes()];
        count = 0;
        source = vertex;
        depthFirstSearch(digraph, vertex);
//        depthFirstSearchByRecursion(digraph,vertex);
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
                edgeTo[current] = stack.peek();
                count++;
                stack.push(current);
            }
        }
    }

    /**
     * 递归的深度优先搜索
     *
     * @param digraph 有向无权图
     * @param vertex  顶点
     */
    private void depthFirstSearchByRecursion(Digraph digraph, int vertex) {
        marked[vertex] = true;
        for (int current :
                digraph.getAdjacencyVertexes(vertex)) {
            if (!marked[current]) {
                depthFirstSearchByRecursion(digraph, current);
            }
        }
    }

    /**
     * 指定顶点是否可达
     *
     * @param vertex vertex
     * @return true：是；false：否
     */
    public boolean marked(int vertex) {
        return marked[vertex];
    }

    /**
     * 连通顶点总数
     *
     * @return 顶点总数
     */
    public int count() {
        return count;
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
