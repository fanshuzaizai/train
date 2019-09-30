package com.github.fanshuzaizai.interview.concurrent.lock;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * <p>
 * 读读可以共存
 * 写写、读写是互斥的
 */
public class ReadWriteLockDemo {

    volatile HashMap<String, String> map = new HashMap<>();

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String k, String v) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        String name = Thread.currentThread().getName();
        System.out.println(String.format("%s 开始put数据", name));
        map.put(k, v);
        System.out.println(String.format("%s put数据完毕", name));
        writeLock.unlock();
    }

    public String get(String k) {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        readLock.lock();
        String name = Thread.currentThread().getName();
        System.out.println(String.format("%s 开始get数据", name));
        String v = map.get(k);
        System.out.println(String.format("%s get数据完毕", name));
        readLock.unlock();
        return v;
    }

    public static void main(String[] args) throws InterruptedException {

        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        CountDownLatch countDownLatch = new CountDownLatch(10);


        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                String nextInt = ThreadLocalRandom.current().nextInt(5) + "";
                String s = readWriteLockDemo.get(nextInt);
                System.out.println(s);
                countDownLatch.countDown();
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                String nextInt = ThreadLocalRandom.current().nextInt(5) + "";
                readWriteLockDemo.put(nextInt, nextInt);
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println("over");
    }

}
