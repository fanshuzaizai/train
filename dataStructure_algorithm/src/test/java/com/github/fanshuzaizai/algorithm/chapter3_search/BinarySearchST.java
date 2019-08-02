package com.github.fanshuzaizai.algorithm.chapter3_search;

import java.util.Arrays;

/**
 * @author Jzy.
 * @date 2019/7/17 17:35
 */
public class BinarySearchST<K extends Comparable<K>, V> {

    private K[] keys;

    private V[] values;

    private int size;

    public BinarySearchST() {
        keys = (K[]) new Comparable[1000];
        values = (V[]) new Object[1000];
    }

    public BinarySearchST(int initCapacity) {
        keys = (K[]) new Comparable[initCapacity];
        values = (V[]) new Object[initCapacity];
    }

    public K min() {
        return keys[0];
    }

    public K max() {
        if (size > 0) {
            return keys[size - 1];
        } else {
            return null;
        }
    }

    public K select(int k) {
        return keys[k];
    }

    public K ceiling(K k) {
        int i = rank(k);
        return keys[i];
    }

    public boolean contains(K k) {
        return get(k) != null;
    }


    public int size() {
        return size;
    }

    /**
     * k应该在的位置
     *
     * @param k
     * @return
     */
    public int rank(K k) {
        return rank(k, 0, size);
    }

    /**
     * <p>
     * 二分查找
     *
     * @param k
     * @param start
     * @param end
     * @return
     */
    public int rank(K k, int start, int end) {
        if (start > end) {
            return start;
        }
        int mid = (end - start) / 2 + start;
        K midKey = keys[mid];
        if (midKey == null) {
            return start;
        }

        if (k.compareTo(midKey) > 0) {
            return rank(k, mid + 1, end);
        } else if (k.compareTo(midKey) < 0) {
            return rank(k, start, mid - 1);
        } else {
            return mid;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K k) {
        if (isEmpty()) {
            return null;
        }

        int i = rank(k);
        if (i < size && keys[i].compareTo(k) == 0) {
            return values[i];
        } else {
            return null;
        }
    }

    public void put(K k, V v) {

        int i = rank(k);

        //已存在k
        if (i < size && keys[i].compareTo(k) == 0) {
            values[i] = v;
        } else {
            //不存在则插入
            //todo 扩容
            System.arraycopy(keys, i, keys, i + 1, size - i);
            System.arraycopy(values, i, values, i + 1, size - i);
            keys[i] = k;
            values[i] = v;
            size++;
        }
    }

    @Override
    public String toString() {
        return "BinarySearchST{" +
                "\nkeys=" + Arrays.toString(keys) +
                ", \nvalues=" + Arrays.toString(values) +
                ", \nsize=" + size +
                '}';
    }

    public static void main(String[] args) {
        BinarySearchST<Integer, String> st = new BinarySearchST<>();

        st.put(12, "122");
        st.put(13, "133");
        st.put(5, "5");
        st.put(13, "1.3.3");
        st.put(7, "77");

        System.out.println(st);
    }

}
