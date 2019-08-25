package com.github.fanshuzaizai.interview.concurrent.lock;

import java.util.concurrent.CountDownLatch;

/**
 * 使用wait和notify必须要 synchronized
 */
public class wait_notify_Demo {

    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 0; i < 5; i++) {
            int j = i;
            new Thread(() -> {
                while (true) {
                    synchronized (o) {
                        o.notifyAll();
                        try {
                            System.out.println(j + " 线程 准备休眠");
                            o.wait();
                            System.out.println(j + " 线程 唤醒了");
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        countDownLatch.await();

    }

}
