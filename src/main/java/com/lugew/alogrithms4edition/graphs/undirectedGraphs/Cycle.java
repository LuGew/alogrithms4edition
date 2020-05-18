package com.lugew.alogrithms4edition.graphs.undirectedGraphs;

import java.util.Stack;

/**
 * @author lugew
 * @since 2018/4/18
 */
public class Cycle {
    private boolean marked[];
    private boolean hasCycle;

    public Cycle(Graph graph) {
        hasCycle = false;
        this.marked = new boolean[graph.getVertexes()];
        for (int i = 0; i < graph.getVertexes(); i++) {
            if (!marked[i]) {
                depthFirstSearch(graph, i);
            }
        }
    }

    /**
     * 非递归的深度优先搜索
     * p4128允许自环和平行边
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
                    if (current != stackTop) {
                        hasCycle = true;
                        return;
                    }
                }
            }

            if (temp == -1) {
                stack.pop();
            } else {
                marked[temp] = true;
                stack.push(temp);
            }
        }
    }


    /**
     * 是否有环
     *
     * @return true：有；false：无
     */
    public boolean isHasCycle() {
        return hasCycle;
    }


}
