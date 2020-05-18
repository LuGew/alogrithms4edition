package com.lugew.alogrithms4edition.strings.substringSearch;

/**
 * @author Administrator
 * @since 2018/5/25
 */
public class Test {
    public static void main(String[] args) {

    }

    /**
     * 暴力搜索匹配字符串
     *
     * @param pattern 模式
     * @param text    文本
     * @return 匹配位置
     */
    public static int search(String pattern, String text) {
        int patternLength = pattern.length();
        int textLength = text.length();
        for (int i = 0; i <= textLength - patternLength; i++) {
            int k;
            for (k = 0; k < patternLength; k++) {
                if (pattern.charAt(k) != text.charAt(k + i)) {
                    break;
                }
            }
            if (k == patternLength) {
                return i;
            }
        }
        return textLength;
    }

    /**
     * 暴力匹配字符串（显示回退法）
     *
     * @param pattern 模式
     * @param text    文本
     * @return 匹配位置
     */
    public static int search2(String pattern, String text) {
        int patternLength = pattern.length();
        int textLength = text.length();
        int i, j;
        for (i = 0, j = 0; i < textLength && j < patternLength; i++) {
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            } else {
                i -= j;
                j = 0;
            }
        }
        if (j == patternLength) {
            return i - patternLength;
        } else {
            return textLength;
        }
    }

    /**
     * 构造有限状态自动机
     *
     * @param r       进制
     * @param pattern 模式
     */
    private void initializeDFA(int r, String pattern) {
        int patternLength = pattern.length();
        int dfa[][] = new int[r][patternLength];
        dfa[pattern.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < patternLength; j++) {
            for (int c = 0; c < r; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pattern.charAt(j)][j] = j + 1;
            X = dfa[pattern.charAt(j)][X];
        }
    }
}
