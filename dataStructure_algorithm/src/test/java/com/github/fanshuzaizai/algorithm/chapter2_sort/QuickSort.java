package com.github.fanshuzaizai.algorithm.chapter2_sort;

import com.github.fanshuzaizai.dataStructure_algorithm.ArrayUtil;

/**
 * 快速排序
 * 取一个分界元素，左边的都比它小，右边的都比它大
 *
 * @author Jzy.
 * @date 2019/7/12 11:05
 */
public class QuickSort {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int partition = partition(arr, start, end);
        sort(arr, start, partition - 1);
        sort(arr, partition + 1, end);
    }


    /**
     * 取一个值，使数组左边的值都比他小，右边的值都比他大
     *
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] arr, int start, int end) {
        //取第一个值作为分区的分界值
        int midVal = arr[start];

        int left = start;
        int right = end + 1;

        while (true) {
            //从左往右，找出1个 > midVal 的元素
            while (arr[++left] < midVal) {//使用==会在只有几个值的情况下造成 指数级别 的消耗
                if (left == end) {
                    break;
                }
            }
            //从右往左，找出1个 < midVal 的元素
            while (arr[--right] > midVal) {
                //冗余的
                if (right == start) {
                    break;
                }
            }
            //相遇了就退出
            if (left >= right) {
                break;
            }
            //交换这两个值
            ArrayUtil.exchange(arr, left, right);
        }

        ArrayUtil.exchange(arr, start, right);
        return right;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {

            int[] ints = ArrayUtil.generateArr(1000, 2000);
            sort(ints);
            ArrayUtil.isSort(ints);
        }


    }

}
