package com.lugew.alogrithms4edition.strings.stringSorts;

import sorting.elementarySorts.InsertionSort;

/**
 * @author LuGew
 * 高位优先的字符串排序
 * @since 2018/7/30
 */
public class MSD {
    private static int R = 256;
    private static final int M = 15;
    private static String[] aux;

    private static int charAt(String string, int d) {
        if (d < string.length()) {
            return string.charAt(d);
        } else {
            return -1;
        }
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + M) {
            InsertionSort.sort(a, lo, hi, d);
            return;
        }
        int[] count = new int[R + 2];//计算频率
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        for (int r = 0; r < R + 1; r++) {//将频率转换为索引
            count[r + 1] += count[r];
        }
        for (int i = lo; i <= hi; i++) {//数据分类
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        for (int i = lo; i <= hi; i++) {//回写
            a[i] = aux[i - lo];
        }
        //递归以每个字符为键进行排序
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }
}
