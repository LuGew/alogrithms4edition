package com.lugew.alogrithms4edition.graphs.directedGraphs.exercises.p4221;

import graphs.directedGraphs.Digraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LCA最近共同祖先+最短先导路径
 *
 * @author LuGew
 * @since 2018/4/25
 */
public class LowestCommonAncestor {
    private int[] v1;
    private int[] v2;
    private int lowestCommonAncestor;
    private int lowestCommonAncestorLevel;

    public LowestCommonAncestor(Digraph digraph, int v1, int v2) {
        Digraph reverse = digraph.reverse();
        this.v1 = new int[reverse.getVertexes()];
        this.v2 = new int[reverse.getVertexes()];
        lowestCommonAncestor = -1;
        lowestCommonAncestorLevel = reverse.getVertexes();
    }

    /**
     * 两边广度优先搜索寻找最近共同祖先
     *
     * @param digraph 有向无权图
     * @param v1      顶点v1
     * @param v2      顶点v2
     */
    private void breadthFirstSearch(Digraph digraph, int v1, int v2) {
        boolean marked[] = new boolean[digraph.getVertexes()];
        Queue<Integer> queue = new LinkedList<>();
        marked[v1] = true;
        queue.offer(v1);
        int temp = -1;
        int count = 0;
        while (!queue.isEmpty()) {
            temp = queue.peek();
            for (int current :
                    digraph.getAdjacencyVertexes(temp)) {
                if (!marked[current]) {
                    queue.offer(current);
                }
            }
            this.v1[queue.remove()] = count++;
        }

        queue = new LinkedList<>();
        marked = new boolean[digraph.getVertexes()];
        queue.offer(v2);
        temp = -1;
        count = 0;
        while (!queue.isEmpty()) {
            temp = queue.peek();
            for (int current :
                    digraph.getAdjacencyVertexes(temp)) {
                if (!marked[current]) {
                    queue.offer(current);
                }
            }
            this.v2[queue.remove()] = count++;
            if (this.v1[temp] > 0) {
                if (this.v1[temp] + this.v2[temp] < lowestCommonAncestorLevel) {
                    lowestCommonAncestorLevel = this.v1[temp] + this.v2[temp];
                    lowestCommonAncestor = temp;
                }
            }
        }
    }

    /**
     * 最近共同祖先
     *
     * @return 最近共同祖先
     */
    public int getLowestCommonAncestor() {
        return lowestCommonAncestor;
    }

    /**
     * 最短先导路径
     *
     * @return 最短先导路径
     */
    public int getLowestCommonAncestorLevel() {
        return lowestCommonAncestorLevel;
    }
}
