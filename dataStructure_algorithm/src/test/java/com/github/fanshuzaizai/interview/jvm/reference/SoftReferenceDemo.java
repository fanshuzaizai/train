package com.github.fanshuzaizai.interview.jvm.reference;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {


    /*
    软引用，当gc发生时，如果内存不够用，才进行回收
     */
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o);
        SoftReference<Object> softReference = new SoftReference<>(o);
        o = null;
        System.gc();
        System.out.println(softReference.get());
    }
}
