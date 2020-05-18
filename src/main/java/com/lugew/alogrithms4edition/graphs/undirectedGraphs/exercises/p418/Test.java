package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p418;

/**
 * @author lugew
 * @since 2018/4/20
 */
public class Test {
    public static void main(String[] args) {
        Search search = new Search(5);
        System.out.println("count: " + search.getCount());
        System.out.println("marked: " + search.marked(0, 1));
        search.union(0, 1);
        search.union(0, 2);
        System.out.println("count: " + search.getCount());
        System.out.println("marked: " + search.marked(0, 1));

    }
}
