package com.lugew.alogrithms4edition.graphs.directedGraphs.exercises.p4220;

import graphs.directedGraphs.Digraph;
import graphs.directedGraphs.StronglyConnectedComponent;
import graphs.directedGraphs.exercises.p427.Degrees;

/**
 * Euler环
 *
 * @author LuGew
 * @since 2018/4/25
 */
public class Euler {

    //欧拉图标志
    private boolean isEuler;

    /**
     * 当有向无权图的强连通分量数是1且每个顶点的出度和入度一样时，图时欧拉图
     *
     * @param digraph 有向无权图
     */
    public Euler(Digraph digraph) {
        isEuler = false;
        StronglyConnectedComponent stronglyConnectedComponent = new StronglyConnectedComponent(digraph);
        if (stronglyConnectedComponent.count() == 1) {
            Degrees degrees = new Degrees(digraph);
            for (int i = 0; i < digraph.getVertexes(); i++) {
                if (degrees.inDegree(i) != degrees.outDegree(i)) {
                    isEuler = false;
                    return;
                }
            }
            isEuler = true;
        }
    }

    public boolean isEuler() {
        return isEuler;
    }
}
