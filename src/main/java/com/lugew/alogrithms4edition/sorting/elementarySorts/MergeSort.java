package com.lugew.alogrithms4edition.sorting.elementarySorts;

import java.util.Arrays;

/**
 * 三种优化方案
 * 1.不将数组复制到辅助数组
 * 2.小数组使用插入排序
 * 3.在merge前判断a[middle]和a[middle+1]的大小，如果a[middle]<a[middle]则说明数组有序
 * 跳过merge
 * lgN!是基于比较算法的最优比较次数
 *
 * @author lugew
 * @since 2018/5/9
 */
public class MergeSort<Key extends Comparable<Key>> {
    private Key[] keys;
    private Key[] tempKeys;

    public MergeSort(Key[] keys) {
        this.keys = keys;
        this.tempKeys = keys.clone();
    }

    private void sortTopToBottom(int low, int high) {
        if (high <= low) {
            return;
        }
        int middle = low + (high - low) / 2;
        sortTopToBottom(low, middle);
        sortTopToBottom(middle + 1, high);
        merge(low, middle, high);
    }

    private void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            tempKeys[i] = keys[i];
        }
        int k = low;
        int j = middle + 1;
        for (int i = low; i <= high; i++) {
            if (k > middle) {
                keys[i] = tempKeys[j++];
            } else if (j > high) {
                keys[i] = tempKeys[k++];
            } else if (tempKeys[k].compareTo(tempKeys[j]) < 0) {
                keys[i] = tempKeys[k++];
            } else {
                keys[i] = tempKeys[j++];
            }
        }
    }


    private void sortBottomToTop(int low, int high) {
        int n = high - low + 1;
        for (int i = 1; i < n; i += i) {
            for (int j = low; j < n; j += i + i) {
                merge(j, j + i - 1, Math.min(j + i + i, high));
            }
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
        MergeSort<Integer> mergeSort = new MergeSort<>(integer);
        mergeSort.sortTopToBottom(0, 8);
        System.out.println(mergeSort.toString());
    }
}
