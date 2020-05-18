package com.lugew.alogrithms4edition.sorting.elementarySorts;

/**
 * @author lugew
 * @since 2018/5/9
 */
public class SelectionSort<Key extends Comparable<Key>> {
    private Key[] keys;

    public SelectionSort(Key[] keys) {
        this.keys = keys;
    }

    /**
     * selection sort
     * compares ~n^2/2
     * exchange ~n
     *
     * @return sorted array
     */
    public Key[] sort() {
        int min;
        for (int i = 0; i < keys.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < keys.length; j++) {
                if (keys[j].compareTo(keys[min]) < 0) {
                    min = j;
                }
            }
            exchange(i, min);
        }
        return keys;
    }

    private void exchange(int i, int j) {
        Key temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;
    }
}
