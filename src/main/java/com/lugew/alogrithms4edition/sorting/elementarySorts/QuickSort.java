package com.lugew.alogrithms4edition.sorting.elementarySorts;

import java.util.Arrays;

/**
 * @author lugew
 * @since 2018/5/10
 */
public class QuickSort<Key extends Comparable<Key>> {
    private Key[] keys;

    public QuickSort(Key[] keys) {
        this.keys = keys;
    }

    public void sort(int low, int high) {
        if (low >= high) {
            return;
        }
        int middle = partition(low, high);
        sort(low, middle);
        sort(middle + 1, high);
    }

    private int partition(int low, int high) {
        Key temp = keys[low];
        int left = low;
        int right = high + 1;
        while (true) {
            while (keys[++left].compareTo(temp) < 0
                    && left < high) ;
            while (keys[--right].compareTo(temp) > 0 && right > low) ;
            if (left >= right)
                break;
            exchange(left, right);
        }
        exchange(low, right);
        return right;
    }

    private void exchange(int i, int j) {
        Key temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;
    }

    @Override
    public String toString() {
        return "QuickSort{" +
                "keys=" + Arrays.toString(keys) +
                '}';
    }

    public static void main(String[] args) {
        Integer integer[] = {9, 1, 2, 3, 5, 6, 7, 8, 4};
        QuickSort<Integer> quickSort = new QuickSort<>(integer);
        quickSort.sort(0, 8);
        System.out.println(quickSort.toString());
    }
}
