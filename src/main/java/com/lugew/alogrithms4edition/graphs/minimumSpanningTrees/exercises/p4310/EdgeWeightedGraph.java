package com.lugew.alogrithms4edition.graphs.minimumSpanningTrees.exercises.p4310;

import graphs.minimumSpanningTrees.Edge;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 稠密图有权无向图
 *
 * @author LuGew
 * @since 2018/4/29
 */


public class EdgeWeightedGraph {

    private double vertexesAdjacency[][];
    //边数
    private int edgeCount;

    public EdgeWeightedGraph(int vertexes) {
        vertexesAdjacency = new double[vertexes][vertexes];
        edgeCount = 0;
    }

    /**
     * 从输入流中读取图，并初始化图
     *
     * @param scanner 缓冲流
     */
    public EdgeWeightedGraph(Scanner scanner) {
        this(scanner.nextInt());
        scanner.nextInt();
        while (scanner.hasNextInt()) {
            addEdge(scanner.nextInt(),
                    scanner.nextInt(), scanner.nextDouble()
            );
        }
    }


    public int getVertexes() {
        return vertexesAdjacency.length;
    }

    public int getEdges() {
        return edgeCount;
    }


    public void addEdge(int v, int w, double weight) {
        vertexesAdjacency[v][w] = weight;
        vertexesAdjacency[w][v] = weight;
        edgeCount++;
    }

    /**
     * 获取顶点的所有边
     *
     * @param vertex 顶点
     * @return 顶点的所有边
     */
    public Iterable<Integer> getAdjacencyVertexes(int vertex) {
        List<Integer> integers = new LinkedList<>();
        for (int i = 0; i < vertexesAdjacency.length; i++) {
            if (!(vertexesAdjacency[vertex][i] == 0)) {
                integers.add(i);
            }
        }
        return integers;
    }

    /**
     * 获取所有边
     *
     * @return 所有边
     */
    /*public Iterable<Edge> edges() {
        List<Edge> list = new LinkedList<>();
        for (int i = 0; i < vertexesAdjacency.length; i++) {
            for (double e :
                    vertexesAdjacency[i]) {
                if (e.other(i) > i) {
                    list.add(e);
                }
            }
        }
        return list;
    }*/
}

