package com.github.fanshuzaizai.interview;

import sun.misc.VM;

import java.lang.ref.SoftReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/**
 * @author Jzy.
 * @date 2019/8/15 9:47
 */
public class Test {


    public static void main(String[] args) throws InterruptedException {


        String s = String.valueOf("123");
        String s2 = "123";

        short a = 9999;


        System.out.println(s == s2);

        String s3 = new String("123");
        String s4 = new String("123");

        System.out.println(s2 == s3);
        System.out.println(s3 == s4);

        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put(null, null);
        map.get(null);


        BitSet bitSet = new BitSet();
    }

}
