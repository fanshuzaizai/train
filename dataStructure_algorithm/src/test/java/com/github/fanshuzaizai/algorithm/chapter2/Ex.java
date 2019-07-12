package com.github.fanshuzaizai.algorithm.chapter2;

import com.github.fanshuzaizai.dataStructure_algorithm.ArrayUtil;
import com.github.fanshuzaizai.dataStructure_algorithm.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Jzy.
 * @date 2019/7/12 11:50
 */
@RunWith(JUnit4.class)
public class Ex {

    @Test
    public void quick(){

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
    public void insret(){

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

}
