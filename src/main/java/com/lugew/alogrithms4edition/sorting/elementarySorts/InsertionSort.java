package com.lugew.alogrithms4edition.sorting.elementarySorts;

import java.util.Arrays;

/**
 * @author lugew
 * @since 2018/5/9
 */
public class InsertionSort<Key extends Comparable<Key>> {
    private Key[] keys;

    public InsertionSort(Key[] keys) {
        this.keys = keys;
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        for (int i = lo + 1; i <= hi; i++) {
            String temp = a[i];
            int tempIndex = i;
            while (tempIndex - 1 >= lo && a[tempIndex].charAt(d) > temp.charAt(d)) {
                a[tempIndex - 1] = a[tempIndex];
            }
            a[i] = temp;
        }
    }

    /**
     * insertion sort
     * compares ~n^2/4
     * exchange ~n^2/4
     * worst compares ~n^2/2
     * worst exchange ~n^2/2
     * best compares n-1
     * best exchanges 0
     *
     * @return sorted array
     */
    public Key[] sort() {
        for (int i = 1; i < keys.length; i++) {
            int start = i - 1;
            Key key = keys[i];
            while (start >= 0 && keys[start].compareTo(key) > 0) {
                keys[start + 1] = keys[start];
                start--;
            }
            keys[start + 1] = key;
        }
        return keys;
    }


    public static void sort(Comparable[] keys, int left, int high) {
        for (int i = left + 1; i <= high; i++) {
            Comparable temp = keys[i];
            while (i - 1 >= left && keys[i - 1].compareTo(temp) < 0) {
                keys[i] = keys[i - 1];
                i--;
            }
            keys[i] = temp;
        }
    }

    @Override
    public String toString() {
        return "InsertionSort{" +
                "keys=" + Arrays.toString(keys) +
                '}';
    }

    public static void main(String[] args) {
        Integer integer[] = {9, 1, 2, 3, 5, 6, 7, 8, 4};
        InsertionSort<Integer> insertionSort = new InsertionSort<>(integer);
        insertionSort.sort();
        System.out.println(insertionSort.toString());
    }
}
