package com.github.fanshuzaizai.interview.jvm.gc;

public class PrintGCDetails {

    /*
    jvm开启-XX:+PrintGCDetails后
    [gc类型][gc区域:gc前大小->gc后大小(当前区域总大小)]
     */

    public static void main(String[] args) {
        while (true){
            System.out.println(Math.random());
        }
    }
}
