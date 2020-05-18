package com.lugew.alogrithms4edition.strings.stringSorts;

/**
 * @author LuGew
 * 字符串低位优先排序
 * @since 2018/7/30
 */
public class LSD {
    public static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        for (int i = W - 1; i >= 0; i--) {
            int count[] = new int[R + 1];//计算出现频率
            for (int j = 0; j < N; j++) {
                count[a[j].charAt(i) + 1]++;
            }
            for (int j = 0; j < R; j++) {
                count[j + 1] += count[j];//将频率转换为索引
            }

            for (int j = 0; j < N; j++) {
                aux[count[a[j].charAt(i)]++] = a[j];//将元素分类
            }

            for (int j = 0; j < N; j++) {//回写
                a[j] = aux[j];
            }
        }
    }
}
