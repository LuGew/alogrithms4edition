package com.lugew.alogrithms4edition.graphs.directedGraphs.exercises.p426;

import graphs.directedGraphs.Digraph;

/**
 * Digraph测试用例
 *
 * @author LuGew
 * @since 2018/4/25
 */
public class Test {
    public static void main(String[] args) {
        Digraph digraph = new Digraph(5);
        digraph.addEdge(0, 1);
        digraph.addEdge(0, 1);
        digraph.addEdge(1, 2);
        digraph.addEdge(2, 3);
        digraph.addEdge(3, 4);
        System.out.println(digraph.toString());
        System.out.println(digraph.hasEdge(0,1));
        System.out.println(digraph.hasEdge(1,0));
        System.out.println("EdgeWeightedDigraph Reverse:");
        System.out.println(digraph.reverse().toString());

    }
}
