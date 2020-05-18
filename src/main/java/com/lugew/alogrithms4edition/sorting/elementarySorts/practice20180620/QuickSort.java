package com.lugew.alogrithms4edition.sorting.elementarySorts.practice20180620;

import sorting.elementarySorts.InsertionSort;

import java.util.Arrays;

/**
 * @author Administrator
 * @since 2018/6/20
 */
public class QuickSort<T extends Comparable<T>> {
    private T[] keys;
    private static int M = 5;

    public QuickSort(T[] keys) {
        this.keys = keys;
    }

    public void sort(int low, int high) {
        if (low + M >= high) {
            InsertionSort.sort(keys, low, high);
            return;
        }
        T pivot = medianOf3(low, high);
        int middle = partition(low, high, pivot);
        sort(low, middle - 1);
        sort(middle + 1, high);
    }

    /**
     * 三向切分快速排序
     * 在含有重复键值的时,排序接近线性
     *
     * @param low  低地址
     * @param high 高地址
     */
    public void quick3Way(int low, int high) {
        if (low >= high) {
            return;
        }
        int lt = low;
        int i = lt + 1;
        int gt = high;
        T v = keys[low];
        while (i <= gt) {
            int cmp = keys[i].compareTo(v);
            if (cmp > 0) {
                swap(i, gt--);
            } else if (cmp < 0) {
                swap(lt++, i++);
            } else {
                i++;
            }
        }
        quick3Way(low, lt - 1);
        quick3Way(gt + 1, high);
    }

    /**
     * 三取样排序
     *
     * @param low  低地址
     * @param high 高地址
     * @return 切分元素
     */
    private T medianOf3(int low, int high) {
        int middle = (low + high) / 2;
        if (keys[low].compareTo(keys[middle]) < 0) {
            swap(low, middle);
        }
        if (keys[middle].compareTo(keys[high]) < 0) {
            swap(middle, high);
        }
        if (keys[low].compareTo(keys[middle]) < 0) {
            swap(low, middle);
        }
        swap(middle, high - 1);
        return keys[high - 1];
    }

    /**
     * 切分
     *
     * @param low   低地址
     * @param high  高地址
     * @param pivot 切分元素
     * @return 切分元素地址
     */
    public int partition(int low, int high, T pivot) {
        int left = low;
        int right = high - 1;
        while (true) {
            while (keys[++left].compareTo(pivot) > 0) ;
            while (keys[--right].compareTo(pivot) < 0) ;
            if (left >= right) {
                break;
            }
            swap(left, right);
        }
        swap(left, high - 1);
        return left;
    }


    private void swap(int left, int right) {
        T t = keys[left];
        keys[left] = keys[right];
        keys[right] = t;
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
//        quickSort.sort(0, 8);
        quickSort.quick3Way(0, 8);
        System.out.println(quickSort.toString());
    }
}
