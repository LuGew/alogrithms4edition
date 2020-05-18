package com.lugew.alogrithms4edition.graphs.undirectedGraphs;


import java.util.Stack;

/**
 * 深度优先搜索宽接口
 * 构造函数初始化顶点vertex的深度优先搜索，
 * 预处理顶点vertex的深度优先搜索路径，
 * 查询和vertex连通的所有顶点并且统计顶点个数
 *
 * @author lugew
 * @since 2018/4/17
 */
public class DepthFirstSearch {
    //连通性标志 true：是；false：否
    private boolean marked[];
    //连通总数
    private int count;

    /**
     * 构造以vertex为顶点的连通图
     *
     * @param graph  无向无权图
     * @param vertex 顶点
     */
    public DepthFirstSearch(Graph graph, int vertex) {
        marked = new boolean[graph.getVertexes()];
        count = 0;
        depthFirstSearch(graph, vertex);
//        depthFirstSearchByRecursion(graph,vertex);
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
        while (!stack.isEmpty()) {
            int current = getNext(graph, stack.peek());
            if (current == -1) {
                stack.pop();
            } else {
                marked[current] = true;
                count++;
                stack.push(current);
            }
        }
    }

    /**
     * 递归的深度优先搜索
     *
     * @param graph  无向无权图
     * @param vertex 顶点
     */
    private void depthFirstSearchByRecursion(Graph graph, int vertex) {
        marked[vertex] = true;
        for (int current :
                graph.getAdjacencyVertexes(vertex)) {
            if (!marked[current]) {
                depthFirstSearchByRecursion(graph,current);
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
}
