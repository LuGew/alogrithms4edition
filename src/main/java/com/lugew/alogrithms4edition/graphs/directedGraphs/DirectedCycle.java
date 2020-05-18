package com.lugew.alogrithms4edition.graphs.directedGraphs;

import java.util.Stack;

/**
 * 有向无环图寻找环
 *
 * @author lugew
 * @since 2018/4/18
 */
public class DirectedCycle {
    private boolean marked[];
    private boolean stack[];
    private int edgeTo[];
    private Stack<Integer> cycle;
    private boolean hasCycle;

    public DirectedCycle(Digraph graph) {
        hasCycle = false;
        this.marked = new boolean[graph.getVertexes()];
        this.stack = new boolean[graph.getVertexes()];
        this.edgeTo = new int[graph.getVertexes()];
        cycle = new Stack<>();
        for (int i = 0; i < graph.getVertexes(); i++) {
            if (!marked[i]) {
                depthFirstSearch(graph, i);
            }
        }
    }

    /**
     * 非递归的深度优先搜索
     *
     * @param graph  有向无权图
     * @param vertex 顶点
     */
    private void depthFirstSearch(Digraph graph, int vertex) {
        Stack<Integer> stack = new Stack<>();
        marked[vertex] = true;
        this.stack[vertex] = true;
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
                    if (current != stackTop && this.stack[current]) {
                        for (int i = stackTop; i != current; i = edgeTo[i]) {
                            cycle.push(i);
                        }
                        cycle.push(current);
                        cycle.push(stackTop);
                        hasCycle = true;
                        return;
                    }
                }
            }

            if (temp == -1) {
                this.stack[temp] = false;
                stack.pop();
            } else {
                marked[temp] = true;
                this.stack[temp] = true;
                edgeTo[temp] = stackTop;
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

    public Iterable<Integer> cycle() {
        return cycle;
    }


}
