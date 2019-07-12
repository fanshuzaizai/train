package com.github.fanshuzaizai.dataStructure_algorithm;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Jzy.
 * @date 2019/7/1 15:45
 */
public class Test {

    public static void main(String[] args) {

        int a = 1000000000;

        int[] array = IntStream.range(0, a).toArray();

        int b;

        StopWatch stopWatch = new StopWatch();
        for (int i = 0; i < a; i++) {
            b = array[9999];
        }
        stopWatch.over();
    }

}
