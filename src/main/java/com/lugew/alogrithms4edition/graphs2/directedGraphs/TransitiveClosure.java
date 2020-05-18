package com.lugew.alogrithms4edition.graphs2.directedGraphs;

/**
 * @author LuGew
 * 有向图定点对可达性
 * @since 2018/7/25
 */
public class TransitiveClosure {
    private DirectedDFS[] all;//有向图深度优先搜索

    /**
     * 构造函数
     * 传递闭包
     *
     * @param digraph 优先图
     */
    public TransitiveClosure(Digraph digraph) {
        all = new DirectedDFS[digraph.getVertexes()];
        for (int i = 0; i < digraph.getVertexes(); i++) {
            all[i] = new DirectedDFS(digraph, i);
        }
    }

    /**
     * 可达性
     *
     * @param v 顶点
     * @param w 顶点
     * @return 是否
     */
    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }
}
