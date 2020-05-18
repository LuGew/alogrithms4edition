package com.lugew.alogrithms4edition.strings.tries;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LuGew
 * 基于单词查找树的符号表
 * @since 2018/8/1
 */
public class TrieST<Value> {
    private static int R = 256;
    private Node root;

    /**
     * 节点
     * 对应的值
     * 以及字母表节
     */
    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    /**
     * 获取键对应的值
     *
     * @param key 键
     * @return 值
     */
    private Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }

    /**
     * 获取节点对应的值
     * 递归方式
     *
     * @param x   节点
     * @param key 键
     * @param d   索引
     * @return 节点
     */
    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }

        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }


    /**
     * 获取键对应值
     * 非递归方式
     *
     * @param node 节点
     * @param key  键
     * @return 节点
     */
    private Node get(Node node, String key) {
        if (node == null) {
            return null;
        }
        int index = 0;
        while (index < key.length()) {
            char c = key.charAt(index);
            node = node.next[c];
            if (node == null) {
                return null;
            }
            index++;
        }
        return node;
    }

    /**
     * 添加或修改一个键值
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    /**
     * 添加或修改一个键值
     * 非递归方式
     *
     * @param key   键
     * @param value 值
     */
    public void putByNoRecursion(String key, Value value) {
        root = putByNoRecursion(root, key, value);
    }

    /**
     * 添加或修改一个键值
     * 递归方式
     *
     * @param x     节点
     * @param key   键
     * @param value 值
     * @param d     索引
     * @return 节点
     */
    private Node put(Node x, String key, Value value, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.val = value;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, value, d + 1);
        return x;
    }

    /**
     * 添加/修改一个键值
     * 非递归方式
     *
     * @param node  节点
     * @param key   键
     * @param value 值
     * @return 节点
     */
    private Node putByNoRecursion(Node node, String key, Value value) {
        if (node == null) {
            node = new Node();
        }
        Node current = node;
        Node parent = node;
        int index = 0;
        while (index < key.length()) {
            char c = key.charAt(index);
            current = current.next[c];
            if (current == null) {
                current = new Node();
                parent.next[c] = current;
                parent = current;
            }
            index++;
        }
        current.val = value;
        return node;
    }

    /**
     * 所有的键
     *
     * @return 所有的键
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    /**
     * 所有含有前缀的键
     *
     * @param prefix 前缀
     * @return 键集合
     */
    private Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new LinkedList<>();
        collect(get(root, prefix, 0), prefix, queue);
        return null;
    }

    /**
     * 收集键
     *
     * @param node   节点
     * @param prefix 前缀
     * @param queue  队列
     */
    private void collect(Node node, String prefix, Queue<String> queue) {
        if (node == null) {
            return;
        }
        if (node.val != null) {
            queue.offer(prefix);
        }
        for (char c = 0; c < R; c++) {
            collect(node.next[c], prefix + c, queue);
        }
    }

    /**
     * 通配符匹配
     *
     * @param pattern 通配符
     * @return 集合
     */
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new LinkedList<>();
        collect(root, "", pattern, queue);
        return queue;
    }

    private void collect(Node node, String prefix, String pattern, Queue<String> queue) {
        int d = prefix.length();
        if (node == null) {
            return;
        }
        if (d == pattern.length() && node.val != null) {
            queue.offer(prefix);
        }
        char next = pattern.charAt(d);
        for (char i = 0; i < R; i++) {
            if (next == '.' || next == i) {
                collect(node.next[i], prefix + i, pattern, queue);
            }
        }
    }

    /**
     * 最长前缀
     *
     * @param string 字符
     * @return 前缀
     */
    public String longestPrefixOf(String string) {
        int length = search(root, string, 0, 0);
        return string.substring(0, length);
    }

    private int search(Node node, String string, int d, int length) {
        if (node == null) {
            return length;
        }
        if (node.val != null) {
            length = d;
        }
        if (d == string.length()) {
            return length;
        }
        char c = string.charAt(d);
        return search(node.next[c], string, d + 1, length);

    }

    /**
     * 删除
     *
     * @param key 键
     */
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node node, String key, int d) {
        if (node == null) {
            return null;
        }
        if (d == key.length()) {
            node.val = null;
        } else {
            char c = key.charAt(d);
            node.next[c] = delete(node.next[c], key, d + 1);
        }
        if (node.val != null) {
            return node;
        }
        for (char c = 0; c < R; c++) {
            if (node.next[c] != null) {
                return node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TrieST<Integer> trieST1 = new TrieST<>();
        String[] strings = new String[]{
                "0",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9"
        };
        TrieST<Integer> trieST2 = new TrieST<>();
        trieST1.put(strings[0], 0);
        trieST1.put(strings[1], 1);
        trieST1.put(strings[2], 2);
        trieST1.put(strings[3], 3);
        trieST2.putByNoRecursion(strings[0], 0);
        trieST2.putByNoRecursion(strings[1], 1);
        trieST2.putByNoRecursion(strings[2], 2);
        trieST2.putByNoRecursion(strings[3], 3);
    }


}
