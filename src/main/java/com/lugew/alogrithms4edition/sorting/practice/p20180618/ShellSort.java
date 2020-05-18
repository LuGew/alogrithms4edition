package com.lugew.alogrithms4edition.sorting.practice.p20180618;

/**
 * 比较次数n^2
 * 最坏情况下移动次数n^2
 * 最好情况下0
 *
 * @author LuGew
 * @since 2018/6/17
 */
public class ShellSort<T extends Comparable<T>> {
    public ShellSort() {

    }

    public void sort(T[] ts) {
        int h =1;
        while (h<ts.length/3)


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
        ShellSort<Integer> insertionSort = new ShellSort<>();
        insertionSort.sort(integers);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < integers.length; i++) {
            stringBuffer.append(integers[i] + " ");
        }
        System.out.println(stringBuffer.toString());
    }
}
