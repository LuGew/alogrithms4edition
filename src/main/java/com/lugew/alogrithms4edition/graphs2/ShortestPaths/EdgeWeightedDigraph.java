package com.lugew.alogrithms4edition.graphs2.ShortestPaths;

import java.util.LinkedList;
import java.util.List;

/**
 * @author LuGew
 * 有权有向图
 * @since 2018/7/24
 */
public class EdgeWeightedDigraph {
    private int vertexes;//顶点数
    private int edges;//边数
    private List<DirectedEdge>[] adjacentDirectedEdgeList;//邻接表

    /**
     * 构造函数
     *
     * @param vertexes 顶点数
     */
    public EdgeWeightedDigraph(int vertexes) {
        this.vertexes = vertexes;
        this.edges = 0;
        this.adjacentDirectedEdgeList = new List[vertexes];
        for (int i = 0; i < vertexes; i++) {
            adjacentDirectedEdgeList[i] = new LinkedList<>();
        }
    }

    /**
     * 添加一条边
     * 允许自环和平行边
     *
     * @param directedEdge 有向边
     */
    public void addEdge(DirectedEdge directedEdge) {
        adjacentDirectedEdgeList[directedEdge.from()].add(directedEdge);
        edges++;
    }

    /**
     * 顶点的邻接顶点
     *
     * @param vertex 顶点
     * @return 邻接顶点
     */
    public Iterable<DirectedEdge> adjacentDirectedEdges(int vertex) {
        return adjacentDirectedEdgeList[vertex];
    }

    //最常用的图处理

    /**
     * 顶点度数
     *
     * @param vertex 顶点
     * @return 度数
     */
    public int degree(int vertex) {
        return adjacentDirectedEdgeList[vertex].size();
    }

    /**
     * 最大度数
     *
     * @return 度数
     */
    public int maxDegree() {
        int max = 0;
        for (int i = 0; i < vertexes; i++) {
            if (adjacentDirectedEdgeList[i].size() > max) {
                max = adjacentDirectedEdgeList[i].size();
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
            for (DirectedEdge directedEdge :
                    adjacentDirectedEdgeList[i]) {
                if (directedEdge.to() == i) {
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
    public EdgeWeightedDigraph reverse() {
        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(vertexes);
        for (int i = 0; i < vertexes; i++) {
            for (DirectedEdge directedEdge :
                    adjacentDirectedEdges(i)) {
                edgeWeightedDigraph.addEdge(directedEdge);
            }
        }
        return edgeWeightedDigraph;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(vertexes + " vertexes," + edges + " edges\n");
        for (int i = 0; i < vertexes; i++) {
            stringBuffer.append(i + ":");
            for (DirectedEdge directedEdge :
                    adjacentDirectedEdgeList[i]) {
                stringBuffer.append(directedEdge + " ");
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
