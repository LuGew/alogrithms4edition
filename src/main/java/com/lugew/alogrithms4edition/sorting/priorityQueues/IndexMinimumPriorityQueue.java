package com.lugew.alogrithms4edition.sorting.priorityQueues;

/**
 * 索引优先队列
 *
 * @author LuGew
 * @since 2018/5/1
 */
public class IndexMinimumPriorityQueue<Key extends Comparable<Key>> {

    private int size;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    public IndexMinimumPriorityQueue(int max) {
        if (max < 0) throw new IllegalArgumentException();
        size = 0;
        keys = (Key[]) new Comparable[max + 1];
        pq = new int[max + 1];
        qp = new int[max + 1];
        for (int i = 0; i <= max; i++)
            qp[i] = -1;
    }

    public void insert(int index, Key key) {
        if (contains(index)) throw new IllegalArgumentException("index is already in the priority queue");
        size++;
        qp[index] = size;
        pq[size] = index;
        keys[index] = key;
        swim(size);
    }

    public void change(int index, Key key) {
        keys[index] = key;
        swim(qp[index]);
        sink(qp[index]);
    }

    public boolean contains(int index) {
        return qp[index] != -1;
    }

    public void delete(int index) {

    }

    public Key minimum() {
        return keys[pq[1]];
    }

    public int mininumIndex() {
        return pq[1];
    }

    public int deleteMinimum() {
        int indexOfMin = pq[1];
        exchange(1, size--);
        sink(1);
        keys[pq[size + 1]] = null;
        qp[pq[size + 1]] = -1;
        return indexOfMin;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 上浮
     *
     * @param k 索引
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exchange(k, k / 2);
            k = k / 2;
        }
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
            exchange(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exchange(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
}
