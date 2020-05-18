package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p417;

import graphs.undirectedGraphs.Graph;

import java.util.Scanner;

/**
 * graph从命令行接收图
 *
 * @author lugew
 * @since 2018/4/20
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println("enter N to exist:");
        String temp = "";
        while (!temp.equals("N")) {
            stringBuffer.append(temp + "\n");
            temp = scanner.nextLine();
        }
        scanner = new Scanner(stringBuffer.toString());
        Graph graph = new Graph(scanner);
        System.out.println(graph.toString());
        scanner.close();
    }
}
