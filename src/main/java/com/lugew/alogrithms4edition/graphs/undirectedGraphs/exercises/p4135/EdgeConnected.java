package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4135;

import graphs.undirectedGraphs.Graph;

import java.util.Stack;

/**
 * 检测图的边连通性
 *
 * @author LuGew
 * @since 2018/4/23
 */
public class EdgeConnected {
    //环内顶点总数
    private int count;
    //是否在环内
    private boolean inCycle[];

    /**
     * 构造以vertex为顶点的连通图
     *
     * @param graph 无向无权图
     */
    public EdgeConnected(Graph graph) {
        inCycle = new boolean[graph.getVertexes()];
        count = 0;
        for (int i = 0; i < graph.getVertexes(); i++) {
            if (!inCycle[i]) {
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
        boolean marked[] = new boolean[graph.getVertexes()];
        Stack<Integer> stack = new Stack<>();
        marked[vertex] = true;
        stack.push(vertex);
        int temp;
        while (!stack.isEmpty()) {
            temp = stack.peek();
            for (int current :
                    graph.getAdjacencyVertexes(temp)) {
                if (!marked[current]) {
                    temp = current;
                    break;
                } else {
                    if (current == temp && stack.size() != 2) {
                        while (!stack.isEmpty()) {
                            inCycle[stack.pop()] = true;
                            count++;
                        }
                    }
                }
            }
            if (temp == stack.peek()) {
                stack.pop();
            } else {
                marked[temp] = true;
                stack.push(temp);
            }
        }
    }

    /**
     * 是否是边连通图
     *
     * @return true：是；false：否
     */
    public boolean isEdgeConnected() {
        return count == inCycle.length;
    }
}
