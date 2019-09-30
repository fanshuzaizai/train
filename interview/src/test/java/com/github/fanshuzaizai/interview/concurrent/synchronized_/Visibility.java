package com.github.fanshuzaizai.interview.concurrent.synchronized_;

import java.util.concurrent.CountDownLatch;


/**
 * condition 相当于 Object 的 wait和notify方法，但使用wait和notify必须要 synchronized
 */
public class Visibility {

    Object o = new Object();

    boolean hasContent = false;

    public void put() throws InterruptedException {
        synchronized (o) {
            while (hasContent) {
                o.wait();
            }
            System.out.println("开始put内容");
            Thread.sleep(500);
            hasContent = true;
            o.notifyAll();
        }

    }

    public void get() throws InterruptedException {
        synchronized (o) {
            while (!hasContent) {
                o.wait();
            }
            System.out.println("开始get内容");
            Thread.sleep(500);
            hasContent = false;
            o.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);
        Visibility conditionDemo = new Visibility();

        new Thread(() -> {
            while (true) {
                try {
                    conditionDemo.put();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    conditionDemo.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        countDownLatch.await();
    }

}
