package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4116;

import graphs.undirectedGraphs.Graph;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * @author Administrator
 * @since 2018/4/19
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new FileInputStream("E:\\LuGew\\Documents\\Books\\algorithm 4/largeG.txt"));
        long start = System.currentTimeMillis();

        Graph graph = new Graph(scanner);
        long middle = System.currentTimeMillis();
        System.out.println("middle: " + (middle - start) / 1000 + "s");

        long end = System.currentTimeMillis();

        System.out.println("spend: " + (end - middle) / 1000 + "s");
        

    }
}
