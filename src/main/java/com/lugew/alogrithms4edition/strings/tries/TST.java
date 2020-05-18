package com.lugew.alogrithms4edition.strings.tries;

/**
 * @author LuGew
 * 三向单词查找树符号表
 * @since 2018/8/2
 */
public class TST<Value> {
    private Node root;

    private class Node {
        char c;
        Node left;
        Node middle;
        Node right;
        Value value;
    }

    public Value get(String key) {
        Node current = get(root, key, 0);
        if (current != null) {
            return current.value;
        }
        return null;
    }

    private Node get(Node node, String key, int d) {
        if (node == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < node.c) {
            return get(node.left, key, d);
        } else if (c > node.c) {
            return get(node.right, key, d);
        } else if (d < key.length() - 1) {
            return get(node.middle, key, d + 1);
        } else {
            return node;
        }
    }

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node node, String key, Value value, int d) {

        char c = key.charAt(d);
        if (node == null) {
            node = new Node();
            node.c = c;
        }
        if (c < node.c) {
            node.left = put(node.left, key, value, d);
        } else if (c > node.c) {
            node.right = put(node.right, key, value, d);
        } else if (d < key.length() - 1) {
            node.middle = put(node.middle, key, value, d + 1);
        } else {
            node.value = value;
        }
        return node;
    }
}
