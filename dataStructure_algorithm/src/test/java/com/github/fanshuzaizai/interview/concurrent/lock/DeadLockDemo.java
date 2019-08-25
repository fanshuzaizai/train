package com.github.fanshuzaizai.interview.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLockDemo {

    final Object o1 = new Object();
    final Object o2 = new Object();

    private void lock_1() {
        synchronized (o1) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "获得了 lock1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "尝试获取 lock2");
            lock_2();
        }
    }

    private synchronized void lock_2() {
        synchronized (o2) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "获得了 lock2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "尝试获取 lock1");
            lock_1();
        }
    }


    public static void main(String[] args) {

        DeadLockDemo deadLockDemo = new DeadLockDemo();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        new Thread(() -> {
            deadLockDemo.lock_2();
        }, "B").start();

        new Thread(() -> {
            deadLockDemo.lock_1();
        }, "A").start();


    }


}
