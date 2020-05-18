package com.lugew.alogrithms4edition.graphs.shortestPaths;

import graphs.minimumSpanningTrees.Edge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 有权有向图
 *
 * @author LuGew
 * @since 2018/4/28
 */
public class EdgeWeightedDigraph {
    //边
    private List<DirectedEdge> edges[];
    //顶点数
    private int vertexes;
    //边数
    private int edgeCount;

    public EdgeWeightedDigraph(int vertexes) {
        edges = new LinkedList[vertexes];
        this.vertexes = vertexes;
        edgeCount = 0;
        for (int i = 0; i < vertexes; i++) {
            edges[i] = new LinkedList<>();
        }
    }

    /**
     * 从输入流中读取图，并初始化图
     *
     * @param scanner 缓冲流
     */
    public EdgeWeightedDigraph(Scanner scanner) {
        this(scanner.nextInt());
        scanner.nextInt();
        while (scanner.hasNextInt()) {
            addEdge(new DirectedEdge(scanner.nextInt(),
                    scanner.nextInt(), scanner.nextDouble())
            );
        }
    }


    public int getVertexes() {
        return vertexes;
    }

    public int getEdges() {
        return edgeCount;
    }


    public void addEdge(DirectedEdge directedEdge) {
        edges[directedEdge.from()].add(directedEdge);
        edgeCount++;
    }

    /**
     * 获取顶点的所有边
     *
     * @param vertex 顶点
     * @return 顶点的所有边
     */
    public Iterable<DirectedEdge> getAdjacencyVertexes(int vertex) {
        return edges[vertex];
    }

    /**
     * 获取所有边
     *
     * @return 所有边
     */
    public Iterable<DirectedEdge> edges() {
        List<DirectedEdge> list = new LinkedList<>();
        for (int i = 0; i < vertexes; i++) {
            list.addAll(edges[i]);
        }
        return list;
    }

    @Override
    public String toString() {
        return "EdgeWeightedDigraph{" +
                "edges=" + Arrays.toString(edges) +
                ", vertexes=" + vertexes +
                ", edgeCount=" + edgeCount +
                '}';
    }
}
