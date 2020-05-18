package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4137;

import graphs.undirectedGraphs.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图像填充
 *
 * @author LuGew
 * @since 2018/4/23
 */
public class FloodFill {
    //顶点颜色映射
    private int color[];
    //图
    private Graph graph;


    public FloodFill(Graph graph) {
        this.graph = graph;
        color = new int[graph.getVertexes()];
    }

    /**
     * 为每个顶点着色
     *
     * @param vertex 顶点
     * @param color  颜色
     */
    public void draw(int vertex, int color) {
        this.color[vertex] = color;
    }

    /**
     * 图像填充
     *
     * @param vertex 顶点
     * @param color  颜色
     */
    public void floodFill(int vertex, int color) {
        breadthFirstSearch(vertex, color);
    }

    /**
     * 广度优先搜索
     *
     * @param vertex 顶点
     * @param color  颜色
     */
    private void breadthFirstSearch(int vertex, int color) {
        boolean marked[] = new boolean[graph.getVertexes()];
        Queue<Integer> queue = new LinkedList<>();
        marked[vertex] = true;
        int current = vertex;
        while (true) {
            if (this.color[current] != color) {
                this.color[current] = color;
                for (int temp :
                        graph.getAdjacencyVertexes(current)) {
                    if (!marked[temp] && this.color[temp] != color) {
                        marked[temp] = true;
                        queue.offer(temp);
                    }
                }
            }
            if (queue.isEmpty()) {
                return;
            }
            current = queue.remove();
        }
    }
}
