package com.lugew.alogrithms4edition.sorting.elementarySorts;

/**
 * @author xlg
 * @since 2018/5/9
 */
public class BubbleSort<Key extends Comparable<Key>> {
    private Key[] keys;

    public BubbleSort(Key[] keys) {
        this.keys = keys;
    }

    /**
     * bubble sort
     * compares n^2/2
     * exchanges n^2/4
     * worst exchanges n^2/4
     *
     * @return sorted array
     */
    public Key[] sort() {
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = 0; j < keys.length - 1 - i; j++) {
                if (keys[j].compareTo(keys[j + 1]) > 0) {
                    exchange(i, j);
                }
            }
        }
        return keys;
    }

    private void exchange(int i, int j) {
        Key temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;
    }
}
