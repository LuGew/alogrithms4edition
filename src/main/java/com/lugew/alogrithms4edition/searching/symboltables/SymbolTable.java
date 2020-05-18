package com.lugew.alogrithms4edition.searching.symboltables;

public interface SymbolTable<Key extends Comparable<Key>, Value> {
    boolean put(Key key, Value value);

    Value get(Key key);

    boolean delete(Key key);

    boolean contains(Key key);

    boolean isEmpty();

    int size();

    Key min();

    Key max();

    Key floor(Key key);

    Key ceiling(Key key);

    int rank(Key key);

    Key select(int rank);

    boolean deleteMin();

    boolean deleteMax();

    int size(Key low, Key high);

    Iterable<Key> keys(Key low, Key high);

    Iterable<Key> keys();

}
