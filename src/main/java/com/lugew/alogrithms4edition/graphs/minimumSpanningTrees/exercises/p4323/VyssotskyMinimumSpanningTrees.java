package com.lugew.alogrithms4edition.graphs.minimumSpanningTrees.exercises.p4323;

import fundamentals.caseStudyUnionFind.UnionFind;
import graphs.minimumSpanningTrees.Edge;
import graphs.minimumSpanningTrees.EdgeWeightedGraph;

import java.util.*;

/**
 * Kruskal最小生成树算法
 *
 * @author LuGew
 * @since 2018/4/29
 */
public class VyssotskyMinimumSpanningTrees {
    //最小生成树
//    private Queue<Edge> minimumSpanningTrees;
    //最小生成树权重
    private double weight;

    private boolean inTrees[];

    List<Edge>[] edgesInMinimumSpanningTrees;


    /**
     * 构造
     *
     * @param edgeWeightedGraph 有权无向图
     */
    public VyssotskyMinimumSpanningTrees(EdgeWeightedGraph edgeWeightedGraph) {
//        minimumSpanningTrees = new PriorityQueue<>();
        Queue<Edge> priorityQueue = new PriorityQueue<>(edgeWeightedGraph.getEdges());
        edgesInMinimumSpanningTrees = new LinkedList[edgeWeightedGraph.getVertexes()];
        for (int i = 0; i < edgesInMinimumSpanningTrees.length; i++) {
            edgesInMinimumSpanningTrees[i] = new LinkedList<>();
        }

        for (Edge e :
                edgeWeightedGraph.edges()) {
            vyssotsky(e);
        }
    }

    /**
     * 最小生成树的边
     *
     * @return 边集合
     */
//    public Iterable<Edge> edges() {
//        return minimumSpanningTrees;
//    }

    /**
     * 最小生成树权重
     *
     * @return 权重
     */
    public double weight() {
        return weight;
    }


    public void vyssotsky(Edge e) {
        int x = e.either();
        int y = e.other(x);
        if (inTrees[x] && inTrees[y]) {
            boolean[] marked = new boolean[inTrees.length];
            Stack<Integer> stack = new Stack<>();
            //Stack<Edge> stackEdge = new Stack<>();
            stack.push(x);
            marked[x] = true;
            Edge max = e;
            while (!stack.isEmpty()) {
                Edge edge = getNext(stack.peek(), edgesInMinimumSpanningTrees[stack.peek()], marked);
                if (edge == null) {
                    stack.pop();
                    //stackEdge.pop();
                } else {
                    marked[edge.other(stack.peek())] = true;
                    stack.push(edge.other(stack.peek()));
                    //stackEdge.push(edge);
                    if (edge.getWeight() > max.getWeight()) {
                        max = edge;
                    }
                    if (edge.other(stack.peek()) == y) {
                        removeEdgeInTrees(edge);
                        return;
                    }
                }
            }

        }
        addEdgeToTrees(e);

    }

    public void addEdgeToTrees(Edge edge) {
        int x = edge.either();
        int y = edge.other(x);
        edgesInMinimumSpanningTrees[x].add(edge);
        edgesInMinimumSpanningTrees[y].add(edge);
        inTrees[x] = true;
        inTrees[y] = true;
    }

    public void removeEdgeInTrees(Edge edge) {
        int x = edge.either();
        int y = edge.other(x);
        edgesInMinimumSpanningTrees[x].remove(edge);
        edgesInMinimumSpanningTrees[y].remove(edge);
        inTrees[x] = false;
        inTrees[y] = false;
    }


    public Edge getNext(int vertex, List<Edge> list, boolean marked[]) {
        for (Edge e :
                list) {
            if (!marked[e.other(vertex)]) {
                return e;
            }
        }
        return null;
    }
}
