package com.github.fanshuzaizai.algorithm.chapter2;

/**
 * 归并排序
 *
 * @author Jzy.
 * @date 2019/7/12 11:29
 */
public class MergeSort {

    public static void sort(int[] arr) {
        int[] aux = new int[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int[] aux, int start, int end) {

        if (end == start) {
            return;
        }

        int mid = start + (end - start) / 2;

        //一直递归，直到找到只有2个元素的数组
        sort(arr, aux, start, mid);
        sort(arr, aux, mid + 1, end);
        merge(arr, aux, start, mid, end);
    }

    private static void merge(int[] arr, int[] aux, final int start, final int mid, final int end) {

        if (arr[mid] <= arr[mid + 1]) {
            return;
        }

        int length = end - start + 1;
        //创造一个原始数组的拷贝
        System.arraycopy(arr, start, aux, start, length);

//        System.out.println(String.format("开始排序 ,start:%d,mid:%d,end:%d,原始数组:%s", start, mid, end, Arrays.toString(arr)));

        /*
           第1个数组 start - mid
           第2个数组 mid+1 - end
         */

        //下一次读取到两个数组的位置
        int head1 = start;
        int head2 = mid + 1;

        for (int k = start; k <= end; k++) {
            //说明第1个数组已经走完了，直接放入第二数组的元素
            if (head1 > mid) {
                arr[k] = aux[head2++];
            } else if (head2 > end) { //第2个数组已经走完了
                arr[k] = aux[head1++];
            } else if (aux[head1] <= aux[head2]) {//第1个数组中的元素大，取出来放入，并将第1个数组的指针后移
                arr[k] = aux[head1++];
            } else if (aux[head1] > aux[head2]) {//第2个数组中的元素大，取出来放入，并将第2个数组的指针后移
                arr[k] = aux[head2++];
            }
        }

//        System.out.println(String.format("完成后的数组:%s", Arrays.toString(arr)));
    }

}
