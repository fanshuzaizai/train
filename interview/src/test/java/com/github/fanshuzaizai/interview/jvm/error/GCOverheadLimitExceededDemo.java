package com.github.fanshuzaizai.interview.jvm.error;

import java.util.ArrayList;

public class GCOverheadLimitExceededDemo {

    /*
    GC回收效果太差会抛出错误
    超过98%的资源用来GC且回收的内存不超过2%

     */
    public static void main(String[] args) {
        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
        ArrayList<String> strings = new ArrayList<>();
        int i = 0;

        while (true) {
            strings.add(String.valueOf(i++).intern());//java.lang.OutOfMemoryError: GC overhead limit exceeded
        }
    }
}
