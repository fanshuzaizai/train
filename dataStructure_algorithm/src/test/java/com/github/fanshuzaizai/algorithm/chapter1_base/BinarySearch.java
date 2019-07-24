package com.github.fanshuzaizai.algorithm.chapter1_base;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author Jzy.
 * @date 2019/7/5 11:18
 */
public class BinarySearch {


    public static int find(int[] arr, int element) {

        int length;
        if (ArrayUtils.isEmpty(arr) || element < arr[0] || element > arr[length = arr.length - 1]) {
            return -1;
        }

        int start = 0;
        int end = (length - 1);

        while (start <= end) {
            int center = (end - start) / 2 + start;
            int centerVal = arr[center];
            if (element < centerVal) {
                end = center - 1;
            } else if (element > centerVal) {
                start = center + 1;
            } else {
                return center;
            }
        }

        return -1;
    }

}
