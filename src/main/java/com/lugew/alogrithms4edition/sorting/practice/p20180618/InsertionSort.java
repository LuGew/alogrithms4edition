package com.lugew.alogrithms4edition.sorting.practice.p20180618;

/**
 * 比较次数n^2
 * 最坏情况下移动次数n^2
 * 最好情况下0
 *
 * @author LuGew
 * @since 2018/6/17
 */
public class InsertionSort<T extends Comparable<T>> {
    public InsertionSort() {

    }

    public void sort(T[] ts) {
        for (int i = 1; i < ts.length; i++) {
            T temp = ts[i];
            while (i - 1 >= 0 && temp.compareTo(ts[i - 1]) > 0) {
                ts[i] = ts[i - 1];
                i--;
            }
            ts[i] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{1, 5, 4, 8, 3, 2, 9, 6, 7};
        InsertionSort<Integer> insertionSort = new InsertionSort<>();
        insertionSort.sort(integers);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < integers.length; i++) {
            stringBuffer.append(integers[i] + " ");
        }
        System.out.println(stringBuffer.toString());
    }
}
