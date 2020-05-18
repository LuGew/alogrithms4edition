package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4131;

import graphs.undirectedGraphs.Graph;

import java.util.HashMap;
import java.util.Map;

/**
 * 计算平行边的总数O(logN)
 *
 * @author LuGew
 * @since 2018/4/22
 */
public class CountParallel {
    private Graph graph;
    private int count;
    private Map<String, String> map;

    public CountParallel(Graph graph) {
        map = new HashMap<>();
        this.graph = graph;
        this.count = 0;
        initialize();
    }


    private void initialize() {
        for (int i = 0; i < graph.getVertexes(); i++) {
            for (int current :
                    graph.getAdjacencyVertexes(i)) {
                if (map.containsKey(i + "" + current)) {
                    count++;
                } else {
                    map.put(i + "" + current, i + "" + current);
                }
            }
        }
    }

    public int getCount() {
        return count / 2;
    }
}
