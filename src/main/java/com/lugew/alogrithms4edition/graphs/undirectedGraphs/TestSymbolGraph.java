package com.lugew.alogrithms4edition.graphs.undirectedGraphs;

import java.io.FileNotFoundException;

/**
 * @author lugew
 * @since 2018/4/18
 */
public class TestSymbolGraph {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "E:\\LuGew\\Documents\\Books\\algorithm 4/movies.txt";
        long start = System.currentTimeMillis();
//        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(new Graph(scanner), 0);
//        ConnectedComponent connectedComponent = new ConnectedComponent(new Graph(scanner));
        SymbolGraph symbolGraph = new SymbolGraph(fileName);

        long end = System.currentTimeMillis();
        System.out.println(symbolGraph.contains("111"));
        System.out.println(symbolGraph.contains("15 Minutes (2001)"));
        System.out.println(symbolGraph.index("15 Minutes (2001)"));
        System.out.println(symbolGraph.name(1));
        System.out.println(symbolGraph.graph());
        System.out.println("spend: " + (end - start) / 1000 + "s");
    }
}
