package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p418;

/**
 * union-find 实现
 *
 * @author lugew
 * @since 2018/4/20
 */
public class Search {

    //顶点索引
    private int ids[];
    //顶点连通分量顶点数
    private int idCount[];
    //连通分量数
    private int count;

    /**
     * 构造函数
     *
     * @param count 连通分量/顶点数
     */
    public Search(int count) {
        this.count = count;
        ids = new int[count];
        idCount = new int[count];
        for (int i = 0; i < count; i++) {
            ids[i] = i;
            idCount[i] = 1;
        }
    }

    /**
     * 连通分量数
     *
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * 寻找根
     *
     * @param vertex 顶点
     * @return 根节点索引
     */
    private int find(int vertex) {
        int root = vertex;
        while (root != ids[root]) {
            root = ids[root];
        }
        int current = vertex;
        while (ids[current] != root) {
            ids[current] = root;
            current = ids[vertex];
            vertex = current;
        }
        return root;
    }

    /**
     * 顶点连通性
     *
     * @param from 起点
     * @param to   终点
     * @return true：是；false：否
     */
    public boolean marked(int from, int to) {
        if (from == to) {
            return true;
        }
        return find(from) == find(to);
    }

    /**
     * 两顶点添加边
     *
     * @param from 起点
     * @param to   终点
     */
    public void union(int from, int to) {
        if (from == to) {
            return;
        }
        int fromRoot = find(from);
        int toRoot = find(to);
        if (idCount[fromRoot] > idCount[toRoot]) {
            ids[toRoot] = ids[fromRoot];
            idCount[fromRoot] += idCount[toRoot];
        } else {
            ids[fromRoot] = ids[toRoot];
            idCount[toRoot] += idCount[fromRoot];
        }
        count--;
    }
}
