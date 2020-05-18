package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4110;

import graphs.undirectedGraphs.Graph;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * @author lugew
 * @since 2018/4/20
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new FileInputStream("E:\\LuGew\\Documents\\Books\\algorithm 4/mediumG.txt"));
        Graph graph = new Graph(scanner);
        FindDeleted findDeleted = new FindDeleted(graph);
        System.out.println(findDeleted.getDeleted());
    }
}
