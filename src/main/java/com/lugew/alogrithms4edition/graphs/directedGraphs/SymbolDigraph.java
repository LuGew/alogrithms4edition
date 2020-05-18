package com.lugew.alogrithms4edition.graphs.directedGraphs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * 有向无权符号图
 *
 * @author LuGew
 * @since 2018/4/24
 */
public class SymbolDigraph {
    //顶点名映射id
    private Map<String, Integer> idMap;
    //id映射顶点名
    private String nameMap[];
    //无向无权无符号图
    private Digraph digraph;

    public SymbolDigraph(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        idMap = new HashMap<>();
        String name;
        while (scanner.hasNextLine()) {
            name = scanner.nextLine();
            String names[] = name.split("/");
            for (int i = 0; i < names.length; i++) {
                if (!idMap.containsKey(names[i])) {
                    idMap.put(names[i], idMap.size());
                }
            }
        }
        nameMap = new String[idMap.size()];
        for (String n :
                idMap.keySet()) {
            nameMap[idMap.get(n)] = n;
        }

        digraph = new Digraph(nameMap.length);
        scanner = new Scanner(new FileInputStream(fileName));
        while (scanner.hasNextLine()) {
            name = scanner.nextLine();
            String names[] = name.split("/");
            int current = idMap.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                digraph.addEdge(current, idMap.get(names[i]));
            }
        }
    }

    public String kevinBacon(String from, String to) {
        if (idMap.get(from) == null || idMap.get(to) == null) {
            return "not exist vertex";
        } else {
            BreadthFirstDirectedPaths breadthFirstDirectedPaths = new BreadthFirstDirectedPaths(digraph, idMap.get(from));
            if (breadthFirstDirectedPaths.hasPathTo(idMap.get(to))) {
                Iterator<Integer> iterator = breadthFirstDirectedPaths.pathTo(idMap.get(to));
                if (iterator != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    while (iterator.hasNext()) {
                        stringBuffer.append(nameMap[iterator.next()] + " ");
                    }
                    return stringBuffer.toString();
                }
            }
        }
        return "null";
    }

    /**
     * p4125
     *
     * @param from 起点
     * @param to   终点
     * @return 迭代器
     */
    public Iterator<Integer> kevinBaconDipthFisrtSearch(String from, String to) {
        if (idMap.get(from) == null || idMap.get(to) == null) {
            return null;
        } else {
            DepthFirstDirectedPaths depthFirstDirectedPaths = new DepthFirstDirectedPaths(digraph, idMap.get(from));
            BreadthFirstDirectedPaths breadthFirstDirectedPaths = new BreadthFirstDirectedPaths(digraph, idMap.get(from));
            if (depthFirstDirectedPaths.hasPathTo(idMap.get(to))) {
                Iterator<Integer> iterator = breadthFirstDirectedPaths.pathTo(idMap.get(to));
                if (iterator != null) {
                    return iterator;
                }
            }
        }
        return null;
    }

    /**
     * p4124
     *
     * @param from 起点
     * @param to   终点
     * @return 迭代器
     */
    public Iterator<Integer> kevinBacon2(String from, String to) {
        if (idMap.get(from) == null || idMap.get(to) == null) {
            return null;
        } else {
            BreadthFirstDirectedPaths breadthFirstDirectedPaths = new BreadthFirstDirectedPaths(digraph, idMap.get(from));
            if (breadthFirstDirectedPaths.hasPathTo(idMap.get(to))) {
                Iterator<Integer> iterator = breadthFirstDirectedPaths.pathTo(idMap.get(to));
                if (iterator != null) {
                    /*StringBuffer stringBuffer = new StringBuffer();
                    while (iterator.hasNext()) {
                        stringBuffer.append(nameMap[iterator.next()] + " ");
                    }*/
                    return iterator;
                }
            }
        }
        return null;
    }

    /**
     * 图中是否含有该名字的顶点
     *
     * @param name 顶点名
     * @return true：是；false：否
     */
    public boolean contains(String name) {
        return idMap.containsKey(name);
    }

    /**
     * 顶点名相应的顶点索引
     *
     * @param name 顶点名
     * @return 索引
     */
    public Integer index(String name) {
        return idMap.get(name);
    }

    /**
     * 顶点索引相应的顶点名
     *
     * @param vertex 顶点索引
     * @return 顶点名
     */
    public String name(int vertex) {
        return nameMap[vertex];
    }

    /**
     * 获取无符号图
     *
     * @return 无向无权图
     */
    public Digraph digraph() {
        return digraph;
    }
}
