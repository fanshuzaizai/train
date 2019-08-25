package com.github.fanshuzaizai.interview.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 优点：不需要将线程休眠，减少线程切换的资源
 * 缺点：一直在循环，耗费cpu资源
 */
public class SpinLockDemo {

    static AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(String.format("%s开始尝试获取锁", thread.getName()));
        while (!atomicReference.compareAndSet(null, thread)) {
        }
        System.out.println(String.format("%s成功获取锁", thread.getName()));
    }

    public static void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(String.format("%s释放了锁", thread.getName()));
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock();
            unlock();
            countDownLatch.countDown();
        }).start();


        new Thread(() -> {
            lock();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            unlock();
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();
        System.out.println("over");
    }

}
