package com.lugew.alogrithms4edition.graphs.directedGraphs.exercises.p427;

import graphs.directedGraphs.Digraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 顶点的出度和入度
 *
 * @author LuGew
 * @since 2018/4/25
 */
public class Degrees {
    //映射图标志
    private Boolean isMap;
    //入度数组
    private int inDegree[];
    //出度数组
    private int outDegree[];
    //起点集合
    private Queue<Integer> sources;
    //终点集合
    private Queue<Integer> sinks;
    private Queue<Integer> queue;

    /**
     * 构造函数
     *
     * @param digraph 有向无权图
     */
    public Degrees(Digraph digraph) {
        inDegree = new int[digraph.getVertexes()];
        outDegree = new int[digraph.getVertexes()];
        for (int i = 0; i < digraph.getVertexes(); i++) {
            for (int current :
                    digraph.getAdjacencyVertexes(i)) {
                outDegree[i]++;
                inDegree[current]++;
            }
            queue.offer(i);
        }
    }

    public Queue<Integer> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Integer> queue) {
        this.queue = queue;
    }

    /**
     * 顶点的入度
     *
     * @param vertex 顶点
     * @return 入度
     */
    public int inDegree(int vertex) {
        return inDegree[vertex];
    }

    /**
     * 顶点的出度
     *
     * @param vertex 顶点
     * @return 出度
     */
    public int outDegree(int vertex) {
        return outDegree[vertex];
    }

    /**
     * 有向无权图的起点
     *
     * @return 起点集
     */
    public Iterable<Integer> sources() {
        if (sources == null) {
            sources = new LinkedList<>();
            for (int i = 0; i < inDegree.length; i++) {
                if (inDegree[i] == 0) {
                    sources.offer(i);
                }
            }
        }
        return sources;
    }

    /**
     * 有向无权图的终点
     *
     * @return 终点集
     */
    public Iterable<Integer> sinks() {
        if (sinks == null) {
            sinks = new LinkedList<>();
            for (int i = 0; i < outDegree.length; i++) {
                if (outDegree[i] == 0) {
                    sinks.offer(i);
                }
            }
        }
        return sinks;
    }

    /**
     * 图是否是映射图
     *
     * @return true：是；false：否
     */
    public boolean isMap() {
        if (isMap == null) {
            for (int i = 0; i < outDegree.length; i++) {
                if (outDegree[i] != 1) {
                    isMap = false;
                    break;
                }
            }
            isMap = true;
        }
        return isMap;
    }

    public int[] getInDegree() {
        return inDegree;
    }

    public void setInDegree(int[] inDegree) {
        this.inDegree = inDegree;
    }
}
