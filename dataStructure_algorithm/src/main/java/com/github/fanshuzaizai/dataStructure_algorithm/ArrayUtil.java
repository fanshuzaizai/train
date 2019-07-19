package com.github.fanshuzaizai.dataStructure_algorithm;

import java.util.Objects;
import java.util.Random;

/**
 * @author Jzy.
 * @date 2019/7/12 11:05
 */
public class ArrayUtil {

    public static void exchange(Integer[] arr, int x, int y) {
        if (x == y) {
            return;
        }
        if (Objects.equals(arr[x], arr[y])) {
            return;
        }
        Integer tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public static void exchange(int[] arr, int x, int y) {
        if (x == y) {
            return;
        }
        if (arr[x] == arr[y]) {
            return;
        }
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public static void isSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!错误 !!!!!!!!!!!!!! =i:" + i);
                break;
            }
        }
    }

    public static int[] generateArr(int length, int range) {

        int[] arr = new int[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(range);
        }

        return arr;
    }

}
