package com.github.fanshuzaizai.interview.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class PhantomReferenceDemo {

    /*
    ReferenceQueue 引用队列，当引用被回收后，会将 Reference 对象放入队列中
    不能使用 .get() 方法，必须配合 ReferenceQueue 使用
    用于判断引用是否被回收了
     */
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o, referenceQueue);
        o = null;
        System.out.println(referenceQueue.poll());
        System.gc();
        Thread.sleep(500);

        System.out.println(referenceQueue.poll());


    }

}
