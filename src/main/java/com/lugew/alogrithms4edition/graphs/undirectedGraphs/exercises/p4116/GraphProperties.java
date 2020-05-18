package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4116;

import graphs.undirectedGraphs.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的离心率
 *
 * @author lugew
 * @since 2018/4/19
 */
public class GraphProperties {
    //离心率
    private int eccentricityTo[];

    //直径
    private int diameter;
    //半径
    private int radius;
    //最短环
    private int girthTo[];
    //周长
    private int girth;

    public GraphProperties(Graph graph) throws Exception {
        girth = Integer.MAX_VALUE;
        diameter = Integer.MIN_VALUE;
        radius = Integer.MAX_VALUE;
        eccentricityTo = new int[graph.getVertexes()];
        girthTo = new int[graph.getVertexes()];
        if (isConnected(graph)) {
            throw new Exception("not connected graph");
        } else {
            /*for (int i = 0; i < graph.getVertexes(); i++) {
                breadthFirstSearch(graph, i);
            }*/
            for (int i = 0; i < graph.getVertexes(); i++) {
                breadthFirstSearchForGirth(graph, i);
            }
        }
    }

    private boolean isConnected(Graph graph) {
        boolean marked[] = new boolean[graph.getVertexes()];
        int count = 0;
        for (int i = 0; i < graph.getVertexes(); i++) {
            if (count >= 1) {
                return false;
            }
            if (!marked[i]) {
                breadthFirstSearch(graph, i);
                count++;
            }
        }
        return true;
    }


    /**
     * 顶点离心率
     *
     * @param vertex 顶点
     * @return 离心率
     */
    public int eccentricity(int vertex) {
        return eccentricityTo[vertex];
    }


    /**
     * 周长
     *
     * @param graph  图
     * @param vertex 起始顶点
     */
    public void breadthFirstSearchForGirth(Graph graph, int vertex) {
        boolean marked[] = new boolean[graph.getVertexes()];
        int distanceTo[] = new int[graph.getVertexes()];
        Queue<Integer> queue = new LinkedList<>();
        marked[vertex] = true;
        queue.offer(vertex);
        int temp = vertex;
        while (!queue.isEmpty()) {
            temp = queue.remove();
            for (int current :
                    graph.getAdjacencyVertexes(temp)) {
                if (!marked[current]) {
                    marked[current] = true;
                    distanceTo[current] = distanceTo[temp] + 1;
                    queue.offer(current);
                } else {
                    if (current == vertex && distanceTo[temp] != 1) {
                        girthTo[vertex] = distanceTo[temp] + 1;
                        girthTo[temp] = girthTo[vertex];
                        if (girthTo[vertex] < girth) {
                            girth = girthTo[vertex];
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * 非递归的广度优先搜索
     *
     * @param graph  图
     * @param vertex 起始顶点
     */
    public void breadthFirstSearch(Graph graph, int vertex) {
        boolean marked[] = new boolean[graph.getVertexes()];
        int distanceTo[] = new int[graph.getVertexes()];
        Queue<Integer> queue = new LinkedList<>();
        marked[vertex] = true;
        queue.offer(vertex);
        int temp = vertex;
        while (!queue.isEmpty()) {
            temp = queue.remove();
            for (int current :
                    graph.getAdjacencyVertexes(temp)) {
                if (!marked[current]) {
                    marked[current] = true;
                    distanceTo[current] = distanceTo[temp] + 1;
                    queue.offer(current);
                } /*else {
                    if (current == vertex && distanceTo[temp] != 1) {
                        girthTo[vertex] = distanceTo[temp] + 1;
                        girthTo[temp] = girthTo[vertex];
                     }
                }*/
            }
        }
        eccentricityTo[vertex] = distanceTo[temp] + 1;
        if (eccentricityTo[vertex] > diameter) {
            diameter = eccentricityTo[vertex];
        }

        if (eccentricityTo[vertex] < radius) {
            radius = eccentricityTo[vertex];
        }
    }

    /**
     * 直径
     *
     * @return 直径
     */
    public int diameter() {
        return diameter;
    }

    /**
     * 半径
     *
     * @return 半径
     */
    public int radius() {
        return radius;
    }

    /**
     * 中心
     *
     * @return 中心
     */
    public int center() {
        for (int i = 0; i < eccentricityTo.length; i++) {
            if (eccentricity(i) == radius)
                return i;
        }
        return -1;
    }

    /**
     * 周长
     *
     * @return 周长
     */
    public int girth() {
        return girth;
    }

    public int getVertex() {
        return eccentricityTo.length;
    }

}
