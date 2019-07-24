package com.github.fanshuzaizai.algorithm.chapter2_sort;

import com.github.fanshuzaizai.dataStructure_algorithm.ArrayUtil;

/**
 * 3向切分的快速排序
 *
 * @author Jzy.
 * @date 2019/7/12 13:31
 */
public class QuickSort_3_Part {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        //取第一个值作为分区的分界值
        int midVal = arr[start];

        //[start  ～ lt-1]都是 小于 分界值的
        int lt = start;
        //[lt  ～ i-1]都是 等于 分界值的
        int i = start;
        //[i ～ gt-1]未知的

        //[gt  ～ end]都是 大于 分界值的
        int gt = end + 1;

        while (i < gt) {

            //相等，则 i 右移
            if (arr[i] == midVal) {
                i++;
            } else if (arr[i] > midVal) {// 大于，则和 gt 前一位交换
                ArrayUtil.exchange(arr, i, gt - 1);
                gt--;
            } else if (arr[i] < midVal) {//小于，则和lt交换
                ArrayUtil.exchange(arr, i, lt);
                lt++;
                i++;
            }
        }
        //排序前半部分
        sort(arr, start, lt - 1);
        //排序后半部分
        sort(arr, gt, end);
    }

    public static void main(String[] args) {
        int length = 1000;
        int[] ints = ArrayUtil.generateArr(10000, 10000);
//        System.out.println(Arrays.toString(ints));

        sort(ints);

        ArrayUtil.isSort(ints);
//        System.out.println(Arrays.toString(ints));

    }


}
