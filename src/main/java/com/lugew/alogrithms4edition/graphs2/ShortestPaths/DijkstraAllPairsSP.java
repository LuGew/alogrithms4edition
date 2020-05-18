package com.lugew.alogrithms4edition.graphs2.ShortestPaths;


/**
 * @author LuGew
 * 顶点对的最短路径
 * @since 2018/7/27
 */
public class DijkstraAllPairsSP {
    private DijkstraSP[] dijkstraShortestPaths;

    public DijkstraAllPairsSP(EdgeWeightedDigraph edgeWeightedDigraph) {
        dijkstraShortestPaths = new DijkstraSP[edgeWeightedDigraph.getVertexes()];
        for (int i = 0; i < edgeWeightedDigraph.getVertexes(); i++) {
            dijkstraShortestPaths[i] = new DijkstraSP(edgeWeightedDigraph, i);
        }
    }

    /**
     * 是否含有路径
     *
     * @param from 起点
     * @param to   终点
     * @return 是否含有路径
     */
    public boolean hasPathTo(int from, int to) {
        return dijkstraShortestPaths[from].hasPathTo(to);
    }

    /**
     * 路径
     *
     * @param from 起点
     * @param to   终点
     * @return 路径
     */
    public Iterable<DirectedEdge> path(int from, int to) {
        return dijkstraShortestPaths[from].pathTo(to);
    }

    /**
     * 距离
     *
     * @param from 起点
     * @param to   终点
     * @return 距离
     */
    public double dist(int from, int to) {
        return dijkstraShortestPaths[from].distTo(to);
    }
}
