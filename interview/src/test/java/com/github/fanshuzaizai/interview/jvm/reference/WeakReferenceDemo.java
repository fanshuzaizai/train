package com.github.fanshuzaizai.interview.jvm.reference;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {


    /*
    弱引用，只要gc发生，就进行回收
     */
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o);
        WeakReference<Object> weakReference = new WeakReference<>(o);
        o = null;
        System.gc();
        System.out.println(weakReference.get());
    }
}
