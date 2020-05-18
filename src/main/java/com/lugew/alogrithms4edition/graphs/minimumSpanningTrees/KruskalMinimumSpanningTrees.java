package com.lugew.alogrithms4edition.graphs.minimumSpanningTrees;

import fundamentals.caseStudyUnionFind.UnionFind;

import java.util.*;

/**
 * Kruskal最小生成树算法
 *
 * @author LuGew
 * @since 2018/4/29
 */
public class KruskalMinimumSpanningTrees {
    //最小生成树
    private Queue<Edge> minimumSpanningTrees;
    //最小生成树权重
    private double weight;

    /**
     * 构造
     *
     * @param edgeWeightedGraph 有权无向图
     */
    public KruskalMinimumSpanningTrees(EdgeWeightedGraph edgeWeightedGraph) {
        minimumSpanningTrees = new LinkedList<>();
        Queue<Edge> priorityQueue = new PriorityQueue<>(edgeWeightedGraph.getEdges());
        UnionFind unionFind = new UnionFind(edgeWeightedGraph.getVertexes());
        while (!priorityQueue.isEmpty() && minimumSpanningTrees.size() < edgeWeightedGraph.getEdges() - 1) {
            Edge edge = priorityQueue.remove();
            int v = edge.either();
            int w = edge.other(v);
            if (!unionFind.connected(v, w)) {
                unionFind.quickUnion(v, w);
                minimumSpanningTrees.offer(edge);
                weight += edge.getWeight();
            }
        }
    }

    /**
     * 最小生成树的边
     *
     * @return 边集合
     */
    public Iterable<Edge> edges() {
        return minimumSpanningTrees;
    }

    /**
     * 最小生成树权重
     *
     * @return 权重
     */
    public double weight() {
        return weight;
    }

    /**
     * 添加一条能够加入最小生成树的边的最大值
     *
     * @param v 顶点v
     * @param w 顶点w
     * @return 权重
     */
    public double addEdgeWeight(int v, int w) {
        List<Edge> list[] = new LinkedList[minimumSpanningTrees.size() + 1];
        boolean marked[] = new boolean[minimumSpanningTrees.size() + 1];
        while (!minimumSpanningTrees.isEmpty()) {
            Edge edge = minimumSpanningTrees.remove();
            int x = edge.either();
            int y = edge.other(x);
            list[x].add(edge);
            list[y].add(edge);
        }

        Stack<Edge> stackEdge = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        marked[v] = true;
        while (!stack.isEmpty()) {
            Edge edge = getNext(stack.peek(), list[stack.peek()], marked);
            if (edge == null) {
                stack.pop();
                stackEdge.pop();
            } else {
                marked[edge.other(stack.peek())] = true;
                stack.push(edge.other(stack.peek()));
                stackEdge.push(edge);
                if (edge.other(stack.peek()) == w) {
                    break;
                }
            }
        }
        double maximum = Double.NEGATIVE_INFINITY;
        for (Edge e :
                stackEdge) {
            if (e.getWeight() > maximum) {
                maximum = e.getWeight();
            }
        }
        return maximum;
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
