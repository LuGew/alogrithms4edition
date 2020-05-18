package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4110;

import graphs.undirectedGraphs.ConnectedComponent;
import graphs.undirectedGraphs.Graph;

import java.util.Stack;

/**
 * 证明p4110
 *
 * @author lugew
 * @since 2018/4/20
 */
public class FindDeleted {
    //访问标志
    private boolean marked[];
    //能被删除顶点
    private int deleted;

    public FindDeleted(Graph graph) throws Exception {
        ConnectedComponent connectedComponent = new ConnectedComponent(graph);
        if (connectedComponent.count() > 1) {
            throw new Exception("not connected graph");
        } else {
            deleted = -1;
            marked = new boolean[graph.getVertexes()];
            depthFirstSearch(graph, 0);
        }
    }


    public int getDeleted() {
        return deleted;
    }

    /**
     * 寻找顶点
     *
     * @param graph  图
     * @param vertex 顶点
     */
    private void depthFirstSearch(Graph graph, int vertex) {
        Stack<Integer> stack = new Stack<>();
        marked[vertex] = true;
        stack.push(vertex);
        while (!stack.isEmpty()) {
            int temp = stack.peek();
            boolean flag = true;
            for (int current :
                    graph.getAdjacencyVertexes(temp)) {
                if (!marked[current]) {
                    marked[current] = true;
                    stack.push(current);
                    flag = false;
                }
            }
            if (flag) {
                deleted = temp;
                return;
            }

        }
    }
}
