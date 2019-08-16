package com.github.fanshuzaizai.interview.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jzy.
 * @date 2019/8/15 10:54
 */
public class ABA {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(1);

        /*
        有个线程将值进行修改，又改回去了
         */
        new Thread(() -> {
            atomicInteger.compareAndSet(1, 2);
            System.out.println(Thread.currentThread().getName() + "=当前值：" + atomicInteger.get());
            atomicInteger.compareAndSet(2, 1);
            System.out.println(Thread.currentThread().getName() + "=当前值：" + atomicInteger.get());
        }).start();

        /*
        当前线程感知不到曾经发生过变化
         */
        Thread.sleep(1000);
        atomicInteger.compareAndSet(1, 3);
        System.out.println(Thread.currentThread().getName() + "=当前值：" + atomicInteger.get());
    }
}
