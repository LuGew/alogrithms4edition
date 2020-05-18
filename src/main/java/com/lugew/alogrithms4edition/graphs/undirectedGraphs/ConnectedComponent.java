package com.lugew.alogrithms4edition.graphs.undirectedGraphs;


import java.util.Stack;

/**
 * 无权无向图的连通分量
 *
 * @author lugew
 * @since 2018/4/18
 */
public class ConnectedComponent {
    private boolean marked[];
    private int edgeTo[];
    private int ids[];
    private int count;

    public ConnectedComponent(Graph graph) {
        this.count = 0;
        this.edgeTo = new int[graph.getVertexes()];
        this.marked = new boolean[graph.getVertexes()];
        this.ids = new int[graph.getVertexes()];
        for (int i = 0; i < graph.getVertexes(); i++) {
            if (!marked[i]) {
                depthFirstSearch(graph, i);
                count++;
            }
        }
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
                ids[current] = count;
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
     * 判断两个顶点是否连通
     *
     * @param from 起点
     * @param to   终点
     * @return true：是；false：否；
     */
    public boolean connected(int from, int to) {
        return ids[from] == ids[to];
    }

    /**
     * 连通分量数
     *
     * @return
     */
    public int count() {
        return count;
    }

    /**
     * 顶点所在连通分量标志
     *
     * @param vertex 顶点
     * @return 连通分量标志
     */
    public int id(int vertex) {
        return ids[vertex];
    }
}
