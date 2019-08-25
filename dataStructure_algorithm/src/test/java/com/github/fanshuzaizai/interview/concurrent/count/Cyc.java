package com.github.fanshuzaizai.interview.concurrent.count;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 是 递增的，达到设定数目都后再放行
 */
public class Cyc {

    public static void main(String[] args) throws InterruptedException {


        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙====");
        });


        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " -》启动");
                try {
                    //阻塞，全部完成后再一起放行
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 结束");
            }, i + "").start();
        }

        Thread.sleep(1000);
        System.out.println("over");
    }

}
