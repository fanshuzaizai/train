package com.github.fanshuzaizai.algorithm.chapter2_sort;

/**
 * 根据插入排序进行修改，每次进行部分排序
 *
 * @author Jzy.
 * @date 2019/7/12 11:08
 */
public class ShellSort {

    public static void sort(int[] arr) {
        int length = arr.length;
        //第一次间隔
        int gap = length / 2;

        while (gap >= 1) {
            //从 gap 位置开始
            for (int i = gap; i < length; i++) {
                int tmp = arr[i];
                int j = i;
                //和前面 current-gap、current-gap*2 依次进行对比，如果小于，则将元素往后移动一个位置，
                while (j - gap >= 0 && tmp < arr[j - gap]) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = tmp;
            }
            gap /= 2;
        }
    }

}
