package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4136;

import graphs.undirectedGraphs.Graph;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 欧拉图
 *
 * @author LuGew
 * @since 2018/4/23
 */
public class EulideanGraph {
    //顶点名映射id
    private Map<String, Integer> idMap;
    //id映射顶点名
    private String nameMap[];
    //无向无权无符号图
    private Graph graph;

    public EulideanGraph(String fileName) throws FileNotFoundException {
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

        graph = new Graph(nameMap.length);
        scanner = new Scanner(new FileInputStream(fileName));
        while (scanner.hasNextLine()) {
            name = scanner.nextLine();
            String names[] = name.split("/");
            int current = idMap.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                graph.addEdge(current, idMap.get(names[i]));
            }
        }
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
    public Graph graph() {
        return graph;
    }

    public int getVertex() {
        return graph.getVertexes();
    }
}
