package com.lugew.alogrithms4edition.fundamentals.caseStudyUnionFind;

/**
 * 路径压缩加权union-find算法
 *
 * @author LuGew
 * @since 2018/4/29
 */
public class UnionFind {
    //顶点归属连接节点
    private int edgeTo[];
    //顶点所在连通分量顶点数
    private int count[];

    /**
     * 构造
     *
     * @param vertexes 顶点数
     */
    public UnionFind(int vertexes) {
        edgeTo = new int[vertexes];
        count = new int[vertexes];
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = i;
        }
    }

    /**
     * 连通两个顶点
     *
     * @param v 顶点
     * @param w 顶点
     */
    public void quickUnion(int v, int w) {
        int rootV = quickFind(v);
        int rootW = quickFind(w);
        int sum = count[rootV] + count[rootW];
        if (count[rootV] > count[rootW]) {
            count[rootW] = sum;
            edgeTo[rootV] = rootW;
        } else {
            count[rootV] = sum;
            edgeTo[rootW] = rootV;
        }

    }

    /**
     * 判断两个顶点是否连通
     *
     * @param v 顶点
     * @param w 顶点
     * @return true：是；false：否
     */
    public boolean connected(int v, int w) {
        int rootV = quickFind(v);
        int rootW = quickFind(w);
        if (rootV == rootW) {
            return true;
        }
        return false;
    }

    /**
     * 路径压缩
     *
     * @param vertex 顶点
     * @return 顶点归属根节点
     */
    private int quickFind(int vertex) {
        int temp = vertex;
        while (temp != edgeTo[temp]) {
            temp = edgeTo[temp];
        }
        while (vertex != temp) {
            edgeTo[vertex] = temp;
        }
        return temp;
    }
}
