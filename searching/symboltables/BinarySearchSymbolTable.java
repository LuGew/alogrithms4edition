package searching.symboltables;

import java.util.*;

/**
 * 二分查找的符號表
 *
 * @author LuGew
 */
public class BinarySearchSymbolTable<Key extends Comparable<Key>, Value> extends AbstractSymbolTable<Key, Value> {
    private Key[] keys;
    private Value[] values;

    public BinarySearchSymbolTable(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    @Override
    public boolean delete(Key key) {
        int index = rank(key);
        if (index < size && keys[index].compareTo(key) == 0) {
            for (int i = index; i < size - 1; i++) {
                keys[index] = keys[index + 1];
                values[index] = values[index + 1];
            }
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean put(Key key, Value value) {
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return true;
        }
        for (int j = size; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        size++;
        return true;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            return values[i];
        } else {
            return null;
        }
    }

    @Override
    public Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[size - 1];
    }

    @Override
    public Key floor(Key key) {
        int rank = rank(key);
        return keys[rank - 1];
    }

    @Override
    public Key ceiling(Key key) {
        int rank = rank(key);
        return keys[rank];
    }

    @Override
    public int rank(Key key) {
        return rank(key, 0, size - 1);
    }

    private int rank(Key key, int low, int high) {
        int middle, compareResult;
        while (high >= low) {
            middle = low + (high - low) / 2;
            Key currentKey = keys[middle];
            compareResult = key.compareTo(currentKey);
            if (compareResult > 0) {
                low = middle + 1;
            } else if (compareResult < 0) {
                high = middle - 1;
            } else {
                return middle;
            }
        }
        return low;
    }

    @Override
    public Key select(int rank) {
        return keys[rank];
    }

    @Override
    public Iterable<Key> keys(Key low, Key high) {
        List<Key> keys = new ArrayList<>(size());
        int i = rank(low);
        int j = rank(high);
        for (; i < j; i++) {
            keys.add(this.keys[i]);
        }
        return keys;
    }
}
