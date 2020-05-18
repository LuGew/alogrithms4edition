package com.lugew.alogrithms4edition.sorting.elementarySorts;

import java.util.Arrays;

/**
 * 步长序列
 * 2^i*3^j O(n*log^2n)
 * 已知的最好步长序列是由Sedgewick提出的(1, 5, 19, 41, 109,...)，
 *
 * @author lugew
 * @since 2018/5/9
 */
public class ShellSort<Key extends Comparable<Key>> {
    private Key[] keys;

    public ShellSort(Key[] keys) {
        this.keys = keys;
    }

    /**
     * shell sort
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
        int h = 1;
        while (h < keys.length / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int j = h; j < keys.length; j++) {
                int start = j - h;
                Key key = keys[j];
                while (start >= 0 && keys[start].compareTo(key) > 0) {
                    keys[start + h] = keys[start];
                    start -= h;
                }
                keys[start + h] = key;
            }
            h = h / 3;
        }
        return keys;
    }

    @Override
    public String toString() {
        return "InsertionSort{" +
                "keys=" + Arrays.toString(keys) +
                '}';
    }

    public static void main(String[] args) {
        Integer integer[] = {9, 1, 2, 3, 5, 6, 7, 8, 4};
        ShellSort<Integer> shellSort = new ShellSort<>(integer);
        shellSort.sort();
        System.out.println(shellSort.toString());
    }
}
