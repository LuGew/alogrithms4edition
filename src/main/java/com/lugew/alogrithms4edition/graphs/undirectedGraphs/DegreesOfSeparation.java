package com.lugew.alogrithms4edition.graphs.undirectedGraphs;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lugew
 * @since 2018/4/18
 */
public class DegreesOfSeparation {
    public static void main(String[] args) throws FileNotFoundException {
//        String fileName = "E:\\LuGew\\Documents\\Books\\algorithm 4/movies.txt";
        Scanner scanner = new Scanner(System.in);
        System.out.println("年限过滤:");
        int year = scanner.nextInt();
        String fileName = "D:\\LuGew\\Documents\\Algorithms\\movies.txt";
        long start = System.currentTimeMillis();
        SymbolGraph symbolGraph = new SymbolGraph(fileName);
        long end = System.currentTimeMillis();
        System.out.println(symbolGraph.contains("111"));
        System.out.println(symbolGraph.contains("15 Minutes (2001)"));
        System.out.println(symbolGraph.index("15 Minutes (2001)"));
        System.out.println(symbolGraph.name(1));
//        System.out.println(symbolGraph.graph());
//        System.out.println(symbolGraph.kevinBacon("Primus, Barry","Guillén Cuervo, Fernando"));
        System.out.println("spend: " + (end - start) / 1000 + "s");
//        Iterator<Integer> iterator = symbolGraph.kevinBacon2("Bacon, Kevin", "Kidman, Nicole");
        //
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Iterator<Integer> iterator = symbolGraph.kevinBacon2("Primus, Barry", "Guillén Cuervo, Fernando");
        if (iterator != null) {
            StringBuffer stringBuffer = new StringBuffer();

            while (iterator.hasNext()) {
                String temp = symbolGraph.name(iterator.next());
                String time = temp.substring(temp.length() - 5, temp.length() - 1);
                if (isNumeric(time)) {
                    if (currentYear - Integer.parseInt(time) < year)
                        stringBuffer.append(temp + "\n");
                } else {
                    stringBuffer.append(temp + "\n");
                }
            }
            System.out.println(stringBuffer.toString());
        }
    }
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
