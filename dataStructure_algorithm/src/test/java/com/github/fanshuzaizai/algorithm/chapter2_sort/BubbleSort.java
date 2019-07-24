package com.github.fanshuzaizai.algorithm.chapter2_sort;

import com.github.fanshuzaizai.dataStructure_algorithm.ArrayUtil;

/**
 * @author Jzy.
 * @date 2019/7/16 17:51
 */
public class BubbleSort {


    public static void sort(int[] arr) {

        if (arr == null || arr.length == 1) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] >= arr[i]) {
                    ArrayUtil.exchange(arr, i, j);
                }
            }
        }

    }

}
