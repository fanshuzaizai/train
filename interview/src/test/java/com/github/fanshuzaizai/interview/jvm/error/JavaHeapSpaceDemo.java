package com.github.fanshuzaizai.interview.jvm.error;

public class JavaHeapSpaceDemo {


    /*
    堆内存溢出
     */
    public static void main(String[] args) {
        //-Xmx5m
        String[] strings = new String[1024 * 1024 * 100];

    }

}
