package com.github.fanshuzaizai.algorithm.chapter2_sort;

/**
 * 希尔排序
 * 根据插入排序进行修改，每次进行部分排序
 * 跳着排序，假设有10个元素
 * （1）排序5
 *      1）排序6,1
 *      2）排序7,2
 *      3）排序8,3
 *      4）排序9,4
 * （2）排序4
 *      1)排序
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
