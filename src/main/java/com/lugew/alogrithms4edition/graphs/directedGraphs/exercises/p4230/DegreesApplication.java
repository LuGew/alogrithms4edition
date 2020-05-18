package com.lugew.alogrithms4edition.graphs.directedGraphs.exercises.p4230;

import graphs.directedGraphs.Digraph;
import graphs.directedGraphs.exercises.p427.Degrees;

import java.util.Queue;

/**
 * 基于队列的拓扑排序
 *
 * @author LuGew
 * @since 2018/4/26
 */
public class DegreesApplication {
    private int[] inDegrees;
    private Queue<Integer> queue;
    private Digraph digraph;
    private Queue<Integer> vertexes;

    public DegreesApplication(Digraph digraph) {
        Degrees degrees = new Degrees(digraph);
        this.digraph = digraph;
        inDegrees = degrees.getInDegree();
        queue = degrees.getQueue();
        initialize();
    }

    private void initialize() {
        while (!queue.isEmpty()) {
            for (int current :
                    digraph.getAdjacencyVertexes(queue.remove())) {
                if (--inDegrees[current] == 0) {
                    vertexes.offer(current);
                }
            }
        }
    }

    public Queue<Integer> getVertexes() {
        return vertexes;
    }
}
