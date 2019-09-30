package com.github.fanshuzaizai.interview.jvm.reference;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapDemo {

    /*
    当key无强引用时，发生gc进行回收entry
     */
    public static void main(String[] args) {
        testNormalHashMap();
        System.out.println("=================");
        testWeakHashMap();
    }

    private static void testNormalHashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(new Integer(1), "value1");
        System.gc();
        System.out.println(map);
    }

    private static void testWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        map.put(new Integer(2), "value2");
        System.gc();
        System.out.println(map);
    }

}
