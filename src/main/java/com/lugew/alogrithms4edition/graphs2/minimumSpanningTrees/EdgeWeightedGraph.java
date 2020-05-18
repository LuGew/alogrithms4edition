package com.lugew.alogrithms4edition.graphs2.minimumSpanningTrees;

import java.util.LinkedList;
import java.util.List;

/**
 * @author LuGew
 * 加权无向图
 * @since 2018/7/26
 */
public class EdgeWeightedGraph {
    private int vertexes;//顶点数
    private int edges;//边数
    private List<Edge> adjacentList[];//邻接链表

    /**
     * 构造函数
     *
     * @param vertexes 顶点数
     */
    public EdgeWeightedGraph(int vertexes) {
        this.vertexes = vertexes;
        this.edges = 0;
        adjacentList = new List[vertexes];
        for (int i = 0; i < vertexes; i++) {
            adjacentList[i] = new LinkedList<>();
        }
    }

    /**
     * 顶点数
     *
     * @return 顶点数
     */
    public int vertexes() {
        return vertexes;
    }

    /**
     * 边数
     *
     * @return 边数
     */
    public int edges() {
        return edges;
    }

    /**
     * 添加一条边
     * 允许自环和平行边
     *
     * @param edge 边
     */
    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adjacentList[v].add(edge);
        adjacentList[w].add(edge);
        edges++;
    }

    public Iterable<Edge> adjacentEdges(int vertex) {
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
        return 2 * edges / vertexes;
    }

    /**
     * 自环数
     *
     * @return 自环数
     */
    public int numberOfSelfLoops() {
        int count = 0;
        for (int i = 0; i < vertexes; i++) {
            for (Edge edge :
                    adjacentList[i]) {
                if (edge.other(i) == i) {
                    count++;
                }
            }
        }
        return count;
    }
}
