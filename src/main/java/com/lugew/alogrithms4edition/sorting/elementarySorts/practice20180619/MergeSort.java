package com.lugew.alogrithms4edition.sorting.elementarySorts.practice20180619;

import java.util.Arrays;

/**
 * @author Administrator
 * @since 2018/6/19
 */
public class MergeSort<T extends Comparable<T>> {
    private T[] keys;
    private T[] tempKeys;

    public MergeSort(T[] keys) {
        this.keys = keys;
        this.tempKeys = (T[]) new Comparable[keys.length];
    }

    public void sort(int low, int high) {
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        sort(low, middle);
        sort(middle + 1, high);
        merge(low, middle, high);
    }

    private void merge(int low, int middle, int high) {
        int k = low;
        int l = middle + 1;
        for (int i = low; i <= high; i++) {
            tempKeys[i] = keys[i];
        }
        for (int i = low; i <= high; i++) {
            if (l > high) {
                keys[i] = tempKeys[k++];
            } else if (k > middle) {
                keys[i] = tempKeys[l++];
            } else if (tempKeys[k].compareTo(tempKeys[l]) > 0) {
                keys[i] = tempKeys[l++];
            } else {
                keys[i] = tempKeys[k++];
            }
        }
    }

    public void sortTwo(int low, int high) {
        int n = keys.length;
        for (int i = 1; i < n; i += i) {
            for (int j = low; j < n - i; j += i + i) {
                merge(j, j + i - 1, Math.min(j + i + i - 1, n - 1));
            }
        }
    }

    @Override
    public String toString() {
        return "MergeSort{" +
                "keys=" + Arrays.toString(keys) +
                '}';
    }

    public static void main(String[] args) {
        Integer integer[] = {9, 1, 2, 3, 5, 6, 7, 8, 4};
        MergeSort<Integer> mergeSort = new MergeSort<>(integer);
        mergeSort.sort(0, 8);
        System.out.println(mergeSort.toString());
    }
}
