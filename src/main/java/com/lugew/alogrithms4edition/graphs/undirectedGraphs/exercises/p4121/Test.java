package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4121;

import graphs.undirectedGraphs.SymbolGraph;

import java.io.FileNotFoundException;

/**
 * @author LuGew
 * @since 2018/4/21
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner scanner = new Scanner(new File("d://algorithm4/largeG.txt"));
        String fileName = "D:\\LuGew\\Documents\\Algorithms/movies.txt";
        long start = System.currentTimeMillis();
        SymbolGraph symbolGraph = new SymbolGraph(fileName);
        long end = System.currentTimeMillis();
        System.out.println(symbolGraph.kevinBacon("Oldman, Gary","Bacon, Kevin"));
        System.out.println(symbolGraph.kevinBacon("Jordan Peele","Bacon, Kevin"));
        System.out.println(symbolGraph.kevinBacon("Washington, Denzel","Bacon, Kevin"));
    }
}
