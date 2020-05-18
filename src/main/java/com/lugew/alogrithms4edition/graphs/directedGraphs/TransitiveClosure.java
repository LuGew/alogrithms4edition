package com.lugew.alogrithms4edition.graphs.directedGraphs;

/**
 * 有向无权图顶点对的可达性，传递闭包实现
 *
 * @author LuGew
 * @since 2018/4/24
 */
public class TransitiveClosure {
    //有向无权图
    private DirectedDFS[] all;

    /**
     * 预处理，图的每个顶点进行深度优先搜索
     *
     * @param digraph 有向无权图
     */
    public TransitiveClosure(Digraph digraph) {
        all = new DirectedDFS[digraph.getVertexes()];
        for (int i = 0; i < digraph.getVertexes(); i++) {
            all[i] = new DirectedDFS(digraph, i);
        }
    }

    /**
     * 顶点对的可达性
     *
     * @param from 起点
     * @param to   终点
     * @return true：可达；false：否
     */
    public boolean reachable(int from, int to) {
        return all[from].marked(to);
    }
}
