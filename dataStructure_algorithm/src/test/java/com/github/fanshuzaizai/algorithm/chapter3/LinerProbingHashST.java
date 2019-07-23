package com.github.fanshuzaizai.algorithm.chapter3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 线性探测符号表
 *
 * @author Jzy.
 * @date 2019/7/23 14:49
 */
public class LinerProbingHashST<K extends Comparable<K>, V> {
    private int size;
    private int initCapacity = 16;
    private Node<K, V>[] _arr;

    public LinerProbingHashST() {
        _arr = (Node<K, V>[]) new Node[initCapacity];
    }

    public LinerProbingHashST(int capacity) {
        _arr = (Node<K, V>[]) new Node[capacity];
    }

    public int size() {
        return size;
    }

    public void put(K k, V v) {
        int i;
        Node n;
        //从当前坐标往后寻找往后寻找
        for (i = hash(k) % _arr.length; (n = _arr[i]) != null; i = (i + 1) % _arr.length) {
            //找到了
            if (n.k.equals(k)) {
                n.v = v;
                return;
            }
        }
        //当前坐标是空的，直接替换。可能是计算出来的下标，也可能是往后顺延位置的下标
        _arr[i] = new Node(k, v);
        size++;

        if (size >= _arr.length / 2) {
            resize(_arr.length * 2);
        }
    }

    public V get(K k) {
        int i;
        Node<K, V> n;
        for (i = hash(k) % _arr.length; (n = _arr[i]) != null; i = (i + 1) % _arr.length) {
            //找到了
            if (n.k.equals(k)) {

                return n.v;
            }
        }
        //未找到，直接返回
        return null;
    }

    public void delete(K k) {
        int i;
        Node<K, V> n;
        for (i = hash(k) % _arr.length; (n = _arr[i]) != null; i = (i + 1) % _arr.length) {
            //找到了
            if (n.k.equals(k)) {
                //将当前节点置空
                _arr[i] = null;
                size--;

                //从后面的元素开始遍历
                while ((n = _arr[i = (++i + 1) % _arr.length]) != null) {
                    //如果节点的实际下标和计算的不一致，则删除，重新放入
                    if (hash(n.k) % _arr.length != i) {
                        _arr[i] = null;
                        size--;
                        put(n.k, n.v);
                    }
                }

                if (size > 0 && size == _arr.length / 8) {
                    resize(_arr.length / 2);
                }
            }
        }
    }

    private void resize(int capacity) {
        //当前节点数量大于 数组的 1/8，则扩容
        if (size > 0 && size > _arr.length / 8) {
            LinerProbingHashST<K, V> st = new LinerProbingHashST<>(capacity);
            for (Node<K, V> node : _arr) {
                st.put(node.k, node.v);
            }
            this._arr = st._arr;
        }
    }


    private int hash(K k) {
        return k.hashCode() & 0x7fffffff;
    }

    @Override
    public String toString() {
        return "LinerProbingHashST{" +
                "size=" + size +
                ", _arr=" + Arrays.toString(_arr) +
                '}';
    }

    private class Node<K, V> {
        K k;
        V v;

        Node(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "k=" + k +
                    ", v=" + v +
                    ", i=" + hash(k) % _arr.length +
                    '}';
        }

        private int hash(K k) {
            return k.hashCode() & 0x7fffffff;
        }
    }

    public static void main(String[] args) {
        LinerProbingHashST<Integer, Integer> st = new LinerProbingHashST<>();

        st.put(12, 12);
        st.put(15, 15);
        st.put(11, 11);
        st.put(17, 17);
        st.put(12, 12);
        st.put(999, 999);
        st.put(567, 567);
        st.put(775, 775);

        System.out.println(st);

    }
}
