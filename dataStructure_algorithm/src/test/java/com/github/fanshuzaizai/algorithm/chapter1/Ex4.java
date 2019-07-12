package com.github.fanshuzaizai.algorithm.chapter1;

import com.github.fanshuzaizai.dataStructure_algorithm.StopWatch;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author Jzy.
 * @date 2019/7/4 11:52
 */
@RunWith(JUnit4.class)
public class Ex4 {

    @Before
    public void start() throws IOException {
    }

    @After
    public void end() {
    }

    @Test
    public void test0() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\1Mints.txt"));
        int[] arr = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8).stream().limit(500000).map(e -> Integer.parseInt(e.trim())).mapToInt(e -> e).toArray();

        Arrays.sort(arr);

        int i = BinarySearch.find(arr, 123455);
        System.out.println("坐标: " + i);

    }

    @Test
    public void sum_2_old() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\1Mints.txt"));
        int[] arr = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8).stream().limit(500000).map(e -> Integer.parseInt(e.trim())).mapToInt(e -> e).toArray();

        int length = arr.length;
        System.out.println(length);

        StopWatch stopWatch = new StopWatch();

        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (arr[i] + arr[j] == 0) {
                    count++;
                }
            }
        }

        stopWatch.over();
        System.out.println(count);
    }


    @Test
    public void sum_2() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\1Mints.txt"));
        int[] arr = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8).stream().limit(500000).map(e -> Integer.parseInt(e.trim())).mapToInt(e -> e).toArray();

        int length = arr.length;
        System.out.println(length);

        StopWatch stopWatch = new StopWatch();

        Arrays.sort(arr);
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (Arrays.binarySearch(arr, i + 1, length, -arr[i]) > i) {
                count++;
            }
        }

        stopWatch.over();
        System.out.println(count);
    }

    @Test
    public void sum_3() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\迅雷下载\\algs4-data\\1Mints.txt"));
        int[] arr = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8).stream().limit(5).map(e -> Integer.parseInt(e.trim())).mapToInt(e -> e).toArray();

        int length = arr.length;
        System.out.println(length);

        StopWatch stopWatch = new StopWatch();


        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    int result = arr[i] + arr[j] + arr[k];
                    if (result == 0) {
                        count++;
                    }
                }
            }
        }
        stopWatch.over();
        System.out.println(count);
    }

   /* public void sum_2_old() throws IOException {

    }*/

}
