package com.lugew.alogrithms4edition.strings.stringSorts;

/**
 * @author LuGew
 * 字母表API
 * @since 2018/7/30
 */
public class Alphabet {

    /******************************************************************************
     *  Compilation:  javac Alphabet.java
     *  Execution:    java Alphabet
     *  Dependencies: StdOut.java
     *
     *  A data type for alphabets, for use with string-processing code
     *  that must convert between an alphabet of size R and the integers
     *  0 through R-1.
     *
     *  Warning: supports only the basic multilingual plane (BMP), i.e,
     *           Unicode characters between U+0000 and U+FFFF.
     *
     ******************************************************************************/


    /**
     * The binary alphabet { 0, 1 }.
     */
    public static final Alphabet BINARY = new Alphabet("01");

    /**
     * The octal alphabet { 0, 1, 2, 3, 4, 5, 6, 7 }.
     */
    public static final Alphabet OCTAL = new Alphabet("01234567");

    /**
     * The decimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }.
     */
    public static final Alphabet DECIMAL = new Alphabet("0123456789");

    /**
     * The hexadecimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F }.
     */
    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");

    /**
     * The DNA alphabet { A, C, T, G }.
     */
    public static final Alphabet DNA = new Alphabet("ACGT");

    /**
     * The lowercase alphabet { a, b, c, ..., z }.
     */
    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");

    /**
     * The uppercase alphabet { A, B, C, ..., Z }.
     */

    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    /**
     * The protein alphabet { A, C, D, E, F, G, H, I, K, L, M, N, P, Q, R, S, T, V, W, Y }.
     */
    public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");

    /**
     * The base-64 alphabet (64 characters).
     */
    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    /**
     * The ASCII alphabet (0-127).
     */
    public static final Alphabet ASCII = new Alphabet(128);

    /**
     * The extended ASCII alphabet (0-255).
     */
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);

    /**
     * The Unicode 16 alphabet (0-65,535).
     */
    public static final Alphabet UNICODE16 = new Alphabet(65536);

    private char[] alphabet;
    private int[] inverse;
    private final int R;

    /**
     * 字母表构造函数
     *
     * @param alpha 字符串
     */
    public Alphabet(String alpha) {
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < alpha.length(); i++) {
            char c = alpha.charAt(i);
            if (unicode[c]) {
                throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
            }
            unicode[c] = true;
        }
        alphabet = alpha.toCharArray();
        R = alpha.length();
        inverse = new int[Character.MAX_VALUE];
        for (int i = 0; i < inverse.length; i++) {
            inverse[i] = -1;
        }
        for (int i = 0; i < R; i++) {
            inverse[alphabet[i]] = i;
        }
    }

    public Alphabet(int radio) {
        this.R = radio;
        alphabet = new char[R];
        inverse = new int[R];
        for (int i = 0; i < R; i++) {
            alphabet[i] = (char) i;
        }
        for (int i = 0; i < R; i++) {
            inverse[i] = i;
        }
    }

    public Alphabet() {
        this(256);
    }

    /**
     * 获取字母表索引位置的字符
     *
     * @param index 索引
     * @return 字符
     */
    public char toChar(int index) {
        if (index < 0 || index >= R) {
            throw new IllegalArgumentException("index must be between 0 and " + R + ": " + index);
        }
        return alphabet[index];
    }

    /**
     * 获取字符c的索引
     *
     * @param c 字符c
     * @return 索引
     */
    public int toIndex(char c) {
        if (c > inverse.length || inverse[c] == -1) {
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        }
        return inverse[c];
    }

    /**
     * c在字母表中吗
     *
     * @param c 字符c
     * @return 是否
     */
    public boolean contains(char c) {
        return inverse[c] != -1;
    }

    /**
     * 基数（字母表中字符的数量）
     *
     * @return 数量
     */
    public int R() {
        return R;
    }

    /**
     * 表示一个索引所需要的位数
     *
     * @return 位数
     */
    public int lgR() {
        int lgR = 0;
        for (int i = R - 1; i >= 1; i /= 2) {
            lgR++;
        }
        return lgR;
    }

    /**
     * 将string转换位R进制的整数
     *
     * @param string 字符串
     * @return 整数
     */
    public int[] toIndices(String string) {
        char[] source = string.toCharArray();
        int[] target = new int[string.length()];
        for (int i = 0; i < source.length; i++) {
            target[i] = toIndex(source[i]);
        }
        return target;
    }

    /**
     * 将R进制的整数转换为基于该字母表的字符串
     *
     * @param indices 整数
     * @return 字符串
     */
    public String toChars(int[] indices) {
        StringBuffer stringBuffer = new StringBuffer(indices.length);
        for (int i = 0; i < indices.length; i++) {
            stringBuffer.append(toChar(indices[i]));
        }
        return stringBuffer.toString();
    }

    public int radio() {
        return R;
    }

}

