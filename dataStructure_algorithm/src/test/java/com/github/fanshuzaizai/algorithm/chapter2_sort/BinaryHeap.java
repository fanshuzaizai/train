package com.github.fanshuzaizai.algorithm.chapter2_sort;

import com.github.fanshuzaizai.dataStructure_algorithm.ArrayUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Jzy.
 * @date 2019/7/17 9:12
 */
public class BinaryHeap {

    /**
     * 元素下标：1-N
     */
    private Integer[] _arr;

    /**
     * 长度
     */
    private int N;

    public BinaryHeap() {
        this._arr = new Integer[1000];
    }

    public BinaryHeap(Integer[] arr) {
        this._arr = arr;
        N = arr.length - 1;

        for (int i = arr.length / 2; i >= 1; i--) {
            submerge(i);
        }
    }

    /**
     * 上浮
     */
    private void emerge(int k) {
        while (k > 1) {
            int father = k / 2;
            if (_arr[k] > _arr[father]) {
                ArrayUtil.exchange(_arr, k, father);
                k = father;
            } else {
                break;
            }
        }
    }

    /**
     * 下沉
     */
    private void submerge(int k) {
        while (2 * k <= N) {
            int val = _arr[k];

            //左子节点
            int leftChildIndex = 2 * k;
            //左子节点比当前大的值
            int leftBalance = _arr[leftChildIndex] - val;

            //右子节点
            int rightChildIndex = 2 * k + 1;
            //右子节点比当前大的值
            int rightBalance = rightChildIndex <= N ? _arr[rightChildIndex] - val : -1;

            //比较2个子节点是否比当前大
            if (leftBalance > 0 || rightBalance > 0) {
                if (rightBalance > leftBalance) {
                    ArrayUtil.exchange(_arr, k, rightChildIndex);
                    k = rightChildIndex;
                } else {
                    ArrayUtil.exchange(_arr, k, leftChildIndex);
                    k = leftChildIndex;
                }
            } else {
                break;
            }
        }
    }

    public void insert(int n) {
        _arr[++N] = n;
        emerge(N);
    }

    public Integer delMax() {
        if (N == 0) {
            return null;
        }
        int i = _arr[1];
        //将头和尾交换
        ArrayUtil.exchange(_arr, 1, N);
        _arr[N--] = null;
        //下沉头部
        submerge(1);
        return i;
    }

    public void print() {
        System.out.println(Arrays.toString(_arr));
    }

    public Integer[] sort() {
        Integer[] copy = Arrays.copyOfRange(_arr, 1, _arr.length);

        while (N > 1) {
            ArrayUtil.exchange(copy, 1, N--);
            submerge(1);
        }

        return copy;
    }

    public boolean isSort() {
        for (int i = 1; i < _arr.length; i++) {
            if (2 * i > N) {
                break;
            }
            if (_arr[2 * i] > _arr[i]) {
                System.err.println("====错误，i:" + i);
                return false;
            }
            if (2 * i + 1 <= N && _arr[2 * i + 1] > _arr[i]) {
                System.err.println("====错误，i:" + i);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int length = 20;
        Random random = new Random();

        Integer[] arr = new Integer[length];
        for (int i = 1; i < length; i++) {
            arr[i] = random.nextInt(100);
        }
        BinaryHeap binaryHeap1 = new BinaryHeap(arr);
        binaryHeap1.print();
        System.out.println(binaryHeap1.isSort());


        if (true) {
            return;
        }
        BinaryHeap binaryHeap = new BinaryHeap();


        for (int i = 0; i < length; i++) {
            binaryHeap.insert(random.nextInt(500));
//            binaryHeap.print();
            if (!binaryHeap.isSort()) {
                System.out.println("insert发生错误");
                break;
            }
            if (random.nextInt(5) % 3 == 0) {
                Integer delMax = binaryHeap.delMax();
//                System.out.println(delMax);
//                binaryHeap.print();
                if (!binaryHeap.isSort()) {
                    System.out.println("delMax发生错误");
//                    binaryHeap.print();
                    break;
                }
            }

        }
        binaryHeap.print();
        binaryHeap.isSort();
    }

}
