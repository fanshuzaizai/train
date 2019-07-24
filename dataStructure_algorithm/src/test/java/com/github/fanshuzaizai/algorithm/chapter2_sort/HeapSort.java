package com.github.fanshuzaizai.algorithm.chapter2_sort;

import com.github.fanshuzaizai.dataStructure_algorithm.ArrayUtil;

import java.util.Random;

/**
 * @author Jzy.
 * @date 2019/7/17 11:35
 */
public class HeapSort {


    public static void sort(int[] arr) {
        int N = arr.length - 1;
        //按照堆进行排序
        for (int i = N / 2; i >= 1; i--) {
            submerge(arr, i, N);
        }
        //将最大的放在最后
        while (N > 1) {
            ArrayUtil.exchange(arr, 1, N--);
            submerge(arr, 1, N);
        }

    }


    /**
     * 下沉
     */
    private static void submerge(int[] arr, int k, int range) {
        while (2 * k <= range) {
            int val = arr[k];

            //左子节点
            int leftChildIndex = 2 * k;
            //左子节点比当前大的值
            int leftBalance = arr[leftChildIndex] - val;

            //右子节点
            int rightChildIndex = 2 * k + 1;
            //右子节点比当前大的值
            int rightBalance = rightChildIndex <= range ? arr[rightChildIndex] - val : -1;

            //比较2个子节点是否比当前大
            if (leftBalance > 0 || rightBalance > 0) {
                if (rightBalance > leftBalance) {
                    ArrayUtil.exchange(arr, k, rightChildIndex);
                    k = rightChildIndex;
                } else {
                    ArrayUtil.exchange(arr, k, leftChildIndex);
                    k = leftChildIndex;
                }
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {

        int length = 500;

        Random random = new Random();

        int[] arr = new int[length];
        for (int i = 1; i < length; i++) {
            arr[i] = random.nextInt(2000);
        }

        sort(arr);

        ArrayUtil.isSort(arr);

    }

}
