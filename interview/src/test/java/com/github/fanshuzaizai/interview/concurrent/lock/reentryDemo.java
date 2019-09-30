package com.github.fanshuzaizai.interview.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁demo
 * <p>
 * synchronized 相当于 一个 reentrantLock
 * 作用是防止死锁
 */
public class reentryDemo {

    static ReentrantLock reentrantLock = new ReentrantLock(true);

    public synchronized static void test1() {
        System.out.println("sync-test1..");
        test2();
    }

    public synchronized static void test2() {
        System.out.println("sync-test2..");
    }

    public static void lock_test1() {
        reentrantLock.lock();
        reentrantLock.lock();
        System.out.println("lock-test1..");
        lock_test2();
        reentrantLock.unlock();
    }

    public static void lock_test2() {
        reentrantLock.lock();
        Condition condition = reentrantLock.newCondition();
        System.out.println("lock-test2..");
        reentrantLock.unlock();
    }

    public static void main(String[] args) {

        test1();

        lock_test1();

        System.out.println("over");

    }
}
