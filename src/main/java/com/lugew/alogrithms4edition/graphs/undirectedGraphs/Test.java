package com.lugew.alogrithms4edition.graphs.undirectedGraphs;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author LuGew
 * @since 2018/4/17
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("d://algorithm4/largeG.txt"));
        long start = System.currentTimeMillis();
        Paths paths = new Paths(new Graph(scanner), 0);
        long end = System.currentTimeMillis();
        System.out.println("spend: " + (end - start));
        System.out.println(paths.hasPathTo(1));

        Iterator<Integer> iterator = paths.pathTo(1);
        StringBuffer stringBuffer = new StringBuffer();
        if (iterator != null) {
            while (iterator.hasNext()) {
                stringBuffer.append(iterator.next() + " ");
            }
            System.out.println(stringBuffer.toString());
        }

    }
}
