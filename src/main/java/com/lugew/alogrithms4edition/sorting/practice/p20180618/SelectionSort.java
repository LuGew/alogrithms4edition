package com.lugew.alogrithms4edition.sorting.practice.p20180618;

/**
 * 比较次数n^2
 * 移动次数n
 *
 * @author LuGew
 * @since 2018/6/17
 */
public class SelectionSort<T extends Comparable<T>> {
    public SelectionSort() {

    }

    public void sort(T[] ts) {
        for (int i = 0; i < ts.length - 1; i++) {
            int max = i;
            for (int j = i + 1; j < ts.length; j++) {
                if (ts[j].compareTo(ts[max]) > 0) {
                    max = j;
                }
            }
            swap(max, i, ts);
        }
    }

    private void swap(int i, int j, T[] ts) {
        T temp = ts[i];
        ts[i] = ts[j];
        ts[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{1, 5, 4, 8, 3, 2, 9, 6, 7};
        SelectionSort<Integer> selectionSort = new SelectionSort<>();
        selectionSort.sort(integers);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < integers.length; i++) {
            stringBuffer.append(integers[i] + " ");
        }
        System.out.println(stringBuffer.toString());
    }
}
