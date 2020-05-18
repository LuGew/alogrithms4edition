package com.lugew.alogrithms4edition.graphs.minimumSpanningTrees;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 有权无向图
 *
 * @author LuGew
 * @since 2018/4/28
 */
public class EdgeWeightedGraph {
    //边
    private List<Edge> edges[];
    //顶点数
    private int vertexes;
    //边数
    private int edgeCount;

    public EdgeWeightedGraph(int vertexes) {
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
    public EdgeWeightedGraph(Scanner scanner) {
        this(scanner.nextInt());
        scanner.nextInt();
        while (scanner.hasNextInt()) {
            addEdge(new Edge(scanner.nextInt(),
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


    public void addEdge(Edge edge) {
        edges[edge.getFrom()].add(edge);
        edges[edge.getTo()].add(edge);
        edgeCount++;
    }

    /**
     * 获取顶点的所有边
     *
     * @param vertex 顶点
     * @return 顶点的所有边
     */
    public Iterable<Edge> getAdjacencyVertexes(int vertex) {
        return edges[vertex];
    }

    /**
     * 获取所有边
     *
     * @return 所有边
     */
    public Iterable<Edge> edges() {
        List<Edge> list = new LinkedList<>();
        for (int i = 0; i < vertexes; i++) {
            for (Edge e :
                    edges[i]) {
                if (e.other(i) > i) {
                    list.add(e);
                }
            }
        }
        return list;
    }
}
