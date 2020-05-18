package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4135;

import graphs.undirectedGraphs.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author LuGew
 * @since 2018/4/23
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("D:\\LuGew\\Documents\\Algorithms/mediumG.txt"));
        long start = System.currentTimeMillis();
        EdgeConnected edgeConnected = new EdgeConnected(new Graph(scanner));
        long end = System.currentTimeMillis();
        System.out.println("spend: " + (end - start));
        System.out.println(edgeConnected.isEdgeConnected());
    }
}
