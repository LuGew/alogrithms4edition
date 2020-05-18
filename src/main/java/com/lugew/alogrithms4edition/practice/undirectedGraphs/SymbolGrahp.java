package com.lugew.alogrithms4edition.practice.undirectedGraphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Administrator
 * @since 2018/5/7
 */
public class SymbolGrahp {
    private Graph graph;
    private Map<String, Integer> nameToId;
    private Map<Integer, String> idToName;

    public SymbolGrahp(Scanner scanner) {
        nameToId = new HashMap<>();
        String name;
        while (scanner.hasNext()) {
            name = scanner.next();
            if (!nameToId.containsKey(name)) {
                nameToId.put(name, nameToId.size());
                idToName.put(nameToId.size(), name);
            }
        }


    }

}
