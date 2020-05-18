package com.lugew.alogrithms4edition.sorting.priorityQueues;

/**
 * 利用二插堆实现优先队列
 * 插入和删除复杂度为logN
 *
 * @author lugew
 * @since 2018/6/25
 */
public class MaxPriorityQueue<Key extends Comparable<Key>> {
    private Key[] keys;
    private static final int CAPACITY = 20;
    private int size;

    public MaxPriorityQueue() {
        keys = (Key[]) new Comparable[CAPACITY];
        size = 0;
    }

    public MaxPriorityQueue(Key[] keys) {
        this.keys = keys;
        size = keys.length;
    }

    public MaxPriorityQueue(int max) {
        this.keys = (Key[]) new Comparable[max + 1];
        size = 0;
    }

    /**
     * 插入一个元素
     *
     * @param key 元素
     */
    public void insert(Key key) {
        keys[++size] = key;
        swim(size);
    }

    /**
     * 返回最大值
     *
     * @return 最大值
     */
    public Key max() {
        if (!isEmpty()) {
            return keys[1];
        }
        return null;
    }

    /**
     * 删除并返回最大值
     *
     * @return 最大值
     */
    public Key deleteMax() {
        if (isEmpty()) {
            return null;
        }
        Key max = keys[1];
        swap(1, size--);
        keys[size + 1] = null;
        sink(1);
        return max;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 下沉
     *
     * @param k 索引
     */
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    /**
     * 上浮
     *
     * @param k 索引
     */
    public void swim(int k) {
        while (k >= 2 && keys[k / 2].compareTo(keys[k]) < 0) {
            swap(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 交换元素
     *
     * @param i 索引
     * @param j 索引
     */
    public void swap(int i, int j) {
        Key key = keys[i];
        keys[i] = keys[j];
        keys[j] = key;
    }

    private boolean less(int i, int j) {
        return keys[i].compareTo(keys[j]) < 0;
    }


    public void sort(Key[] array) {
        int N = array.length;
        for (int i = N / 2; i >= 1; i--) {
            sink(array, i, N);
        }
        while (N > 1) {
            swap(array, 1, N--);
            sink(array, 1, N);
        }
    }

    private void sink(Key[] array, int i, int n) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && less(array, j, j + 1)) {
                j++;
            }
            if (!less(array,i, j)) {
                break;
            }
            swap(array,i, j);
            i = j;
        }
    }

    private void swap(Key[] array, int i, int j) {
        Key key = array[i];
        array[i] = array[j];
        array[j] = key;
    }

    private boolean less(Key[] array, int j, int i) {
        return array[i].compareTo(array[j]) < 0;
    }
}
