package com.lugew.alogrithms4edition.graphs.shortestPaths;

/**
 * 任意两点之间的最短路径
 *
 * @author LuGew
 * @since 2018/5/1
 */
public class DijkstraAllPairsShorestPaths {
    private DijkstraShortestPaths dijkstraShortestPaths[];

    public DijkstraAllPairsShorestPaths(EdgeWeightedDigraph edgeWeightedDigraph) {
        dijkstraShortestPaths = new DijkstraShortestPaths[edgeWeightedDigraph.getVertexes()];
        for (int i = 0; i < edgeWeightedDigraph.getVertexes(); i++) {
            dijkstraShortestPaths[i] = new DijkstraShortestPaths(edgeWeightedDigraph, i);
        }
    }

    public Iterable<DirectedEdge> path(int from, int to) {
        return dijkstraShortestPaths[from].pathTo(to);
    }

    public double dist(int from, int to) {
        return dijkstraShortestPaths[from].distanceTo(to);
    }
}
