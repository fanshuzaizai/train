package com.github.fanshuzaizai.algorithm.chapter2_sort;

/**
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
