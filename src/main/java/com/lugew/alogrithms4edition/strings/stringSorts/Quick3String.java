package com.lugew.alogrithms4edition.strings.stringSorts;

/**
 * @author LuGew
 * 三向字符串快速排序
 * @since 2018/7/31
 */
public class Quick3String {
    private static int charAt(String string, int d) {
        if (d < string.length()) {
            return string.charAt(d);
        } else {
            return -1;
        }
    }

    public static void sort(String[] a) {

    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) {
            return;
        }
        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        int v = charAt(a[lo], d);
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) {
                exchange(a, lt++, i++);
            } else if (t > v) {
                exchange(a, gt--, i);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1, d);
        if (v >= 0) {
            sort(a, lt, gt, d + 1);
        }
        sort(a, gt + 1, hi, d);
    }

    private static void exchange(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
