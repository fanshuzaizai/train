package com.github.fanshuzaizai.algorithm.chapter2_sort;

import com.github.fanshuzaizai.dataStructure_algorithm.ArrayUtil;

/**
 * 选择排序，每次从index(i)开始便利，从后面所有的元素中，找出最小的放在i位置上
 *
 * @author Jzy.
 * @date 2019/7/12 11:07
 */
public class ChooseSort {

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            //和余下的依次比较，得出最小的元素
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i != min) {
                ArrayUtil.exchange(arr, i, min);
            }
        }
    }

}
