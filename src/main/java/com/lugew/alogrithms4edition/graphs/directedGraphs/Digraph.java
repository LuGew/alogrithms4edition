package com.lugew.alogrithms4edition.graphs.directedGraphs;


import com.lugew.alogrithms4edition.graphs2.undirectedGraphs.Graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @author LuGew
 * 无权有向图
 * @since 2018/7/24
 */
public class Digraph {
    private int vertexes;//顶点数
    private int edges;//边数
    private List<Integer>[] adjacentList;//邻接表

    /**
     * 构造函数
     *
     * @param vertexes 顶点数
     */
    public Digraph(int vertexes) {
        this.vertexes = vertexes;
        this.edges = 0;
        this.adjacentList = new List[vertexes];
        for (int i = 0; i < vertexes; i++) {
            adjacentList[i] = new LinkedList<>();
        }
    }

    /**
     * 添加一条边
     * 允许自环和平行边
     *
     * @param from 起点
     * @param to   终点
     */
    public void addEdge(int from, int to) {
        adjacentList[from].add(to);
        adjacentList[to].add(from);
        edges++;
    }

    /**
     * 顶点的邻接顶点
     *
     * @param vertex 顶点
     * @return 邻接顶点
     */
    public Iterable<Integer> adjacentVertexes(int vertex) {
        return adjacentList[vertex];
    }

    //最常用的图处理

    /**
     * 顶点度数
     *
     * @param vertex 顶点
     * @return 度数
     */
    public int degree(int vertex) {
        return adjacentList[vertex].size();
    }

    /**
     * 最大度数
     *
     * @return 度数
     */
    public int maxDegree() {
        int max = 0;
        for (int i = 0; i < vertexes; i++) {
            if (adjacentList[i].size() > max) {
                max = adjacentList[i].size();
            }
        }
        return max;
    }

    /**
     * 平均度数
     *
     * @return 平均度数
     */
    public double avgDegree() {
        return edges / vertexes;
    }

    /**
     * 自环数
     *
     * @return 自环数
     */
    public int numberOfSelfLoops() {
        int count = 0;
        for (int i = 0; i < vertexes; i++) {
            for (Integer vertex :
                    adjacentList[i]) {
                if (vertex == i) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 反向图
     *
     * @return 反向图
     */
    public Graph reverse() {
        Graph graph = new Graph(vertexes);
        for (int i = 0; i < vertexes; i++) {
            for (Integer vertex :
                    adjacentVertexes(i)) {
                graph.addEdge(vertex, i);
            }
        }
        return graph;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(vertexes + " vertexes," + edges + " edges\n");
        for (int i = 0; i < vertexes; i++) {
            stringBuffer.append(i + ":");
            for (Integer vertex :
                    adjacentList[i]) {
                stringBuffer.append(vertex + " ");
            }
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    public int getVertexes() {
        return vertexes;
    }

    public void setVertexes(int vertexes) {
        this.vertexes = vertexes;
    }

    public int getEdges() {
        return edges;
    }

    public void setEdges(int edges) {
        this.edges = edges;
    }
}
