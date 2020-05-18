package com.lugew.alogrithms4edition.graphs.directedGraphs.exercises.p4224;

import graphs.directedGraphs.Digraph;
import graphs.directedGraphs.Topological;

import java.util.Iterator;

/**
 * 有向无环图的汉米尔顿路径
 *
 * @author LuGew
 * @since 2018/4/26
 */
public class HamiltonPath {
    private boolean hasHamiltonPath;

    public HamiltonPath(Digraph digraph) {
        Topological topological = new Topological(digraph);
        Iterator<Integer> iterator = topological.order().iterator();
        int current = 0;
        int next = -1;
        if (iterator.hasNext()) {
            next = iterator.next();
        } else {
            hasHamiltonPath = false;
            return;
        }
        while (iterator.hasNext()) {
            current = next;
            next = iterator.next();
            if (!digraph.getAdjacencyVertexes(current).contains(next)) {
                hasHamiltonPath = false;
                break;
            }
        }
        hasHamiltonPath = true;
    }

    public boolean isHasHamiltonPaht() {
        return hasHamiltonPath;
    }
}
