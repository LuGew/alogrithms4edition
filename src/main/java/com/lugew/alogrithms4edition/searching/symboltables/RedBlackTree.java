package com.lugew.alogrithms4edition.searching.symboltables;

/**
 * 紅黑樹
 *
 * @author LuGew
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> extends AbstractSymbolTable<Key, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    @Override
    public boolean put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
        return true;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1, RED);
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            node.left = put(node.left, key, value);
        } else if (compareResult > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    @Override
    public Value get(Key key) {
        return null;
    }

    @Override
    public Key min() {
        return null;
    }

    @Override
    public Key max() {
        return null;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int rank) {
        return null;
    }

    @Override
    public Iterable<Key> keys(Key low, Key high) {
        return null;
    }


    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = RED;
        right.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = RED;
        left.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return left;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int size;
        boolean color;

        public Node(Key key, Value value, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }
    }

}
