package com.lugew.alogrithms4edition.graphs2.undirectedGraphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LuGew
 * @since 2018/7/25
 */
public class SymbolGraph {
    private Map<String, Integer> nameMapId;
    private String[] idMapName;
    private Graph graph;

    public SymbolGraph(String stream, String sp) throws IOException {
        nameMapId = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new StringReader(stream));
        String[] a = bufferedReader.readLine().split(sp);
        for (int i = 0; i < a.length; i++) {
            if (!nameMapId.containsKey(a[i])) {
                nameMapId.put(a[i], nameMapId.size());
            }
        }

        idMapName = new String[nameMapId.size()];
        for (String name :
                nameMapId.keySet()) {
            idMapName[nameMapId.get(name)] = name;
        }
        graph = new Graph(idMapName.length);
        bufferedReader = new BufferedReader(new StringReader(stream));
        String[] temp = bufferedReader.readLine().split(sp);
        int v = nameMapId.get(temp[0]);
        for (int i = 1; i < temp.length; i++) {
            graph.addEdge(v, nameMapId.get(temp[i]));
        }
    }

    /**
     * 含有键
     *
     * @param name 名
     * @return 是否
     */
    public boolean containsKey(String name) {
        return nameMapId.containsKey(name);
    }

    /**
     * 顶点名对应索引
     *
     * @param name 顶点名
     * @return 索引
     */
    public int index(String name) {
        return nameMapId.get(name);
    }

    /**
     * 索引对应顶点名
     *
     * @param vertex 索引
     * @return 顶点名
     */
    public String name(int vertex) {
        return idMapName[vertex];
    }

    /**
     * 无向图
     *
     * @return 无向图
     */
    public Graph graph() {
        return graph;
    }
}
