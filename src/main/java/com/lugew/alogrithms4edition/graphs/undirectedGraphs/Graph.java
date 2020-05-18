package com.lugew.alogrithms4edition.graphs.undirectedGraphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 无权无向图
 * 注意：1.自环；2.平行边。
 * 定义：1.连通图：图中任意一顶点都能到达另一任意顶点；
 * 2.无环图：不包含环的图；
 * 3.树：无环连通图；
 * 4.森林：互不相连的树组成的集合；
 * 5.生成树：含有图中所有顶点的树；
 * 6.生成树森林：所有连通子图的树的集合；
 *
 * @author lugew
 * @since 2018/4/17
 */
public class Graph implements Cloneable {
    //顶点总数
    private int vertexes;
    //边总数
    private int edges;
    //邻接链表
    /**
     * 其他数据结构也可表示
     * 1.邻接矩阵
     * 表示V个顶点需要V^2空间
     * 不支持平行边
     * 2.边的数组
     * 获取顶点的邻接顶点需要遍历所有边
     * 3.邻接表数组/邻接链表
     */
    private List<Integer> adjacencyList[];

    public Graph(int vertexes) {
        this.edges = 0;
        this.vertexes = vertexes;
        adjacencyList = new LinkedList[vertexes];
        for (int i = 0; i < vertexes; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    /**
     * 从输入流中读取图，并初始化图
     *
     * @param scanner 缓冲流
     */
    public Graph(Scanner scanner) {
        this(scanner.nextInt());
        scanner.nextInt();
        while (scanner.hasNextInt()) {
            addEdge(
                    scanner.nextInt(),
                    scanner.nextInt());
        }
    }

    /**
     * 获取顶点个数
     *
     * @return 顶点总数
     */
    public int getVertexes() {
        return vertexes;
    }

    /**
     * 获取边总数
     *
     * @return 边总数
     */
    public int getEdges() {
        return edges;
    }

    /**
     * 增加链接from和to两个顶点的边
     *
     * @param from 起始顶点
     * @param to   终止顶点
     */
    public void addEdge(int from, int to) {
        adjacencyList[from].add(to);
        if (from != to) {
            adjacencyList[to].add(from);
        }
        edges++;
    }

    /**
     * p415
     * 不允许存在平行边和自环
     *
     * @param from 起点
     * @param to   终点
     */
    public void addEdgeExcludeParallelEdgesAndLoops(int from, int to) {
        if (from != to && (!adjacencyList[from].contains(to))) {
            adjacencyList[from].add(to);
            adjacencyList[to].add(from);
            edges++;
        }
    }

    /**
     * 复制图
     *
     * @return 图
     * @throws CloneNotSupportedException 不支持克隆
     */
    public Graph copyGraph() throws CloneNotSupportedException {
        return (Graph) this.clone();
    }

    /**
     * 获取顶点的所有邻接顶点
     *
     * @param vertex 当前顶点
     * @return 顶点的所有邻接顶点
     */
    public List<Integer> getAdjacencyVertexes(int vertex) {
        return adjacencyList[vertex];
    }

    /**
     * p414
     * 顶点是否有边 from-to
     *
     * @param from 起点
     * @param to   终点
     * @return true：有；false：无；
     */
    public boolean hasEdge(int from, int to) {
        return adjacencyList[from].contains(to);
    }

    // 最常用的图处理

    /**
     * 获取顶点的度数
     *
     * @param vertex 顶点
     * @return 度
     */
    public int degree(int vertex) {
        return getAdjacencyVertexes(vertex).size();
    }

    /**
     * 最大度
     *
     * @return 度
     */
    public int maxDegree() {
        int maxDegree = 0;
        for (int i = 0; i < vertexes; i++) {
            int temp = getAdjacencyVertexes(i).size();
            if (temp > maxDegree) {
                maxDegree = temp;
            }
        }
        return maxDegree;
    }

    /**
     * 平均度
     *
     * @return 度
     */
    public double avgDegree() {
        return 2 * edges / vertexes;
    }

    /**
     * 自环数
     *
     * @return 数
     */
    public int numberOfSelfLoops() {
        int count = 0;
        for (int i = 0; i < vertexes; i++) {
            for (int w :
                    getAdjacencyVertexes(i)) {
                if (w == i) {
                    count++;
                }
            }
        }
        return count / 2;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Graph:\n");
        for (int i = 0; i < vertexes; i++) {
            stringBuffer.append(i + ":");
            for (int current :
                    adjacencyList[i]) {
                stringBuffer.append(current + " ");
            }
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }
}
