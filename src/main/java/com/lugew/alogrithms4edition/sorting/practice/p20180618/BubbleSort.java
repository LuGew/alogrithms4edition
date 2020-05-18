package com.lugew.alogrithms4edition.sorting.practice.p20180618;

/**
 * 比较次数n^2^
 * 最坏情况下比较次数n^2
 * 最好情况下移动次数0
 *
 * @author LuGew
 * @since 2018/6/17
 */
public class BubbleSort<T extends Comparable<T>> {
    public BubbleSort() {

    }

    public void sort(T[] ts) {
        for (int i = 0; i < ts.length - 1; i++) {
            for (int j = 0; j < ts.length - 1 - i; j++) {
                if (ts[j].compareTo(ts[j + 1]) > 0) {
                    swap(j, j + 1, ts);
                }
            }
        }
    }

    private void swap(int i, int j, T[] ts) {
        T temp = ts[i];
        ts[i] = ts[j];
        ts[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{1, 5, 4, 8, 3, 2, 9, 6, 7};
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        bubbleSort.sort(integers);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < integers.length; i++) {
            stringBuffer.append(integers[i] + " ");
        }
        System.out.println(stringBuffer.toString());
    }
}
