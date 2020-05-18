package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4113;

import graphs.undirectedGraphs.BreadthFirstPaths;
import graphs.undirectedGraphs.Graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 测试distTo
 *
 * @author lugew
 * @since 2018/4/20
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("E:\\LuGew\\Documents\\Books\\algorithm 4/tinyG.txt"));
        Graph graph = new Graph(scanner);
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, 0);
        System.out.println("shortestDistance :"+breadthFirstPaths.distTo(1));
    }

}
