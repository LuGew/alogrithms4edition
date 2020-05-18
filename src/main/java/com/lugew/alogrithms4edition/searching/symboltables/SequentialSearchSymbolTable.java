package com.lugew.alogrithms4edition.searching.symboltables;

import java.util.ArrayList;
import java.util.List;

/**
 * 基於無序鏈表的符號表
 *
 * @author LuGew
 */
public class SequentialSearchSymbolTable<Key extends Comparable<Key>, Value> extends AbstractSymbolTable<Key, Value> {
    private Node first;

    @Override
    public boolean delete(Key key) {
        Node previous = first;
        Node current = first;
        while (current != null) {
            if (key.equals(current.key)) {
                break;
            } else {
                previous = current;
                current = current.next;
            }
        }
        if (current == null) {
            return false;
        } else {
            previous.next = current.next;
            size--;
            return true;
        }
    }

    @Override
    public boolean put(Key key, Value value) {
        for (Node node = first; node != null; node = node.next) {
            if (key.equals(node.key)) {
                node.value = value;
            }
        }
        first = new Node(key, value, first);
        size++;
        return true;
    }

    @Override
    public Value get(Key key) {
        for (Node node = first; node != null; node = node.next) {
            if (key.equals(node.key)) {
                return node.value;
            }
        }
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
        Node current = first;
        List<Key> keys = new ArrayList<>(size());
        while (current != null) {
            keys.add(current.key);
            current = current.next;
        }
        return keys;
    }

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
