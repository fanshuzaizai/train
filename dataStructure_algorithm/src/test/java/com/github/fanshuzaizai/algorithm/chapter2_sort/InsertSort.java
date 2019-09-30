package com.github.fanshuzaizai.algorithm.chapter2_sort;

/**
 * 插入排序，从index(i)开始，往前遍历，找到小于等于自己的元素，然后放在该位置上，后面的元素都后移一位
 *
 * @author Jzy.
 * @date 2019/7/12 11:06
 */
public class InsertSort {

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //当前元素
            int now = arr[i];
            int j = i;
            //一直往前找，直到找到小于now的位置
            while (--j >= 0 && arr[j] > now) {
            }
            j++;
            if (i != j){
                //将 j 直到 i-1 的元素都后移一格
                System.arraycopy(arr, j, arr, j + 1, i - j);
                //将原来 i 的值放在 j 的位置
                arr[j] = now;
            }
        }
    }

}
