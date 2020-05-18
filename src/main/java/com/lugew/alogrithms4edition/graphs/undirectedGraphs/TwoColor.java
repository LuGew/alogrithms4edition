package com.lugew.alogrithms4edition.graphs.undirectedGraphs;

import java.util.Stack;

/**
 * @author Administrator
 * @since 2018/4/18
 */
public class TwoColor {
    private boolean marked[];
    private boolean color[];
    private boolean isTwoColorable;

    public TwoColor(Graph graph) {
        isTwoColorable = false;
        this.marked = new boolean[graph.getVertexes()];
        this.color = new boolean[graph.getVertexes()];
        for (int i = 0; i < graph.getVertexes(); i++) {
            if (!marked[i]) {
                depthFirstSearch(graph, i);
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
        int temp, stackTop;
        while (!stack.isEmpty()) {
            temp = -1;
            stackTop = stack.peek();
            for (int current :
                    graph.getAdjacencyVertexes(stackTop)) {
                if (!marked[current]) {
                    temp = current;
                    break;
                } else {
                    if (color[current] == color[stackTop]) {
                        isTwoColorable = true;
                        return;
                    }
                }
            }

            if (temp == -1) {
                stack.pop();
            } else {
                marked[temp] = true;
                color[temp] = !color[stackTop];
                stack.push(temp);
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
     * 是否是二分图
     *
     * @return true：是；false：否
     */
    public boolean isTwoColorable() {
        return isTwoColorable;
    }
}
