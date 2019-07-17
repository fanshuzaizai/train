package com.github.fanshuzaizai.algorithm.chapter2;

import com.github.fanshuzaizai.dataStructure_algorithm.ArrayUtil;
import com.github.fanshuzaizai.dataStructure_algorithm.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author Jzy.
 * @date 2019/7/12 11:50
 */
@RunWith(JUnit4.class)
public class Ex {

    @Test
    public void test() {
        String a = "aaa";
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1 < o2) {
                return 1;
            } else if (o1 > o2) {
                return -1;
            } else {
                return 0;
            }
        });
        priorityQueue.add(12);
        System.out.println(priorityQueue);
        priorityQueue.add(14);
        System.out.println(priorityQueue);
        priorityQueue.add(15);
        System.out.println(priorityQueue);
        priorityQueue.add(5);
        System.out.println(priorityQueue);
        priorityQueue.add(3);
        System.out.println(priorityQueue);
        priorityQueue.add(7);
        System.out.println(priorityQueue);

        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue);

        System.out.println(priorityQueue.peek());
        System.out.println(priorityQueue);

    }

    @Test
    public void quick() {

        int[] arr = ArrayUtil.generateArr(10000000, 5);
        int[] arr2 = arr.clone();

        StopWatch stopWatch = new StopWatch("1");
        QuickSort.sort(arr);
        stopWatch.over();
        ArrayUtil.isSort(arr);

        StopWatch stopWatch2 = new StopWatch("2");
        QuickSort_3_Part.sort(arr2);
        stopWatch2.over();
        ArrayUtil.isSort(arr2);

    }

    @Test
    public void insret() {

        int[] arr = ArrayUtil.generateArr(50000000, 10000);
        int[] arr2 = arr.clone();
        int[] arr3 = arr.clone();
        int[] arr4 = arr.clone();
//        int[] clone4 = arr.clone();

        StopWatch stopWatch = new StopWatch("quickSort");
        QuickSort.sort(arr);
        stopWatch.over();
        ArrayUtil.isSort(arr);

        StopWatch stopWatch2 = new StopWatch("mergeSort");
        MergeSort.sort(arr2);
        stopWatch2.over();
        ArrayUtil.isSort(arr2);

        StopWatch stopWatch3 = new StopWatch("shellSort");
        ShellSort.sort(arr3);
        stopWatch3.over();
        ArrayUtil.isSort(arr3);

        StopWatch stopWatch4 = new StopWatch("QuickSort_3_Part");
        QuickSort_3_Part.sort(arr4);
        stopWatch4.over();
        ArrayUtil.isSort(arr4);

    }

    @Test
    public void binary() {
        Random random = new Random();
        BinaryHeap binaryHeap = new BinaryHeap();

        int length = 20;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(100);
        }

        for (int i = 0; i < arr.length; i++) {
            binaryHeap.insert(arr[i]);
        }
        binaryHeap.print();

    }

}
