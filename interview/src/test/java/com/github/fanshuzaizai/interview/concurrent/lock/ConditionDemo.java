package com.github.fanshuzaizai.interview.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition 相当于 Object 的 wait和notify方法，但使用wait和notify必须要 synchronized
 */
public class ConditionDemo {

    ReentrantLock reentrantLock = new ReentrantLock();
    Condition conditionA = reentrantLock.newCondition();
    Condition conditionB = reentrantLock.newCondition();
    Condition conditionC = reentrantLock.newCondition();

    int index = 1;

    public void A() throws InterruptedException {
        reentrantLock.lock();
        /*
        interrupt 和 虚假唤醒可能发生，所以要用循环而不嫩用if
         */
        while (index != 1) {
            conditionA.await();
        }
        for (int i = 1; i <= 1; i++) {
            System.out.println("A-" + i);
        }
        index = 2;
        conditionB.signal();
        reentrantLock.unlock();
    }

    public void B() throws InterruptedException {
        reentrantLock.lock();
        /*
        interrupt 和 虚假唤醒可能发生，所以要用循环而不嫩用if
         */
        while (index != 2) {
            conditionB.await();
        }
        for (int i = 1; i <= 2; i++) {
            System.out.println("B-" + i);
        }
        index = 3;
        conditionC.signal();
        reentrantLock.unlock();
    }

    public void C() throws InterruptedException {
        reentrantLock.lock();
        /*
        interrupt 和 虚假唤醒可能发生，所以要用循环而不嫩用if
         */
        while (index != 3) {
            conditionC.await();
        }
        for (int i = 1; i <= 3; i++) {
            System.out.println("C-" + i);
        }
        index = 1;
        Thread.sleep(500);
        System.out.println("一轮结束===");
        conditionA.signal();
        reentrantLock.unlock();
    }


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        ConditionDemo conditionDemo = new ConditionDemo();

        new Thread(() -> {
            while (true) {
                try {
                    conditionDemo.A();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    conditionDemo.B();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    conditionDemo.C();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        countDownLatch.await();
    }

}
