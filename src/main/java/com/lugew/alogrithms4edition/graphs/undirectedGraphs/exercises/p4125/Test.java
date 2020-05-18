package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4125;

import graphs.undirectedGraphs.SymbolGraph;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LuGew
 * @since 2018/4/22
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "D:\\LuGew\\Documents\\Algorithms\\movies.txt";
        SymbolGraph symbolGraph = new SymbolGraph(fileName);
//        Iterator<Integer> iterator = symbolGraph.kevinBaconDipthFisrtSearch("Bacon, Kevin", "Kidman, Nicole");
        Iterator<Integer> iterator = symbolGraph.kevinBaconDipthFisrtSearch( "Kidman, Nicole","Bacon, Kevin");
        if (iterator != null) {
            StringBuffer stringBuffer = new StringBuffer();

            while (iterator.hasNext()) {

                stringBuffer.append(symbolGraph.name(iterator.next()) + "\n");

            }
            System.out.println(stringBuffer.toString());
        }
    }


}
