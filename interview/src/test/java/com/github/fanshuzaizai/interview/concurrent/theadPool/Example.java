package com.github.fanshuzaizai.interview.concurrent.theadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Example {


    public static void main(String[] args) throws InterruptedException {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,//核心线程数
                10,//最大线程数
                60,//当 maximumPoolSize > corePoolSize 时，多余线程保留的时间
                TimeUnit.SECONDS, // keepAliveTime 的单位
                new LinkedBlockingQueue<>(1),//等待队列
                Executors.defaultThreadFactory(),//线程工厂
                new ThreadPoolExecutor.AbortPolicy()//当maximumPoolSize达到最大后，执行的策略
        );

        /*
        开始任务：
        1.如果线程数没有超过 corePoolSize ，则new一个线程执行任务
        2.如果当前线程数量超过 corePoolSize ，则放入 workQueue
        3.如果 workQueue 满了，且当前线程数量没有超过 maximumPoolSize ，则new一个线程执行任务
        4.如果 当前线程数量超过 maximumPoolSize ，则根据 rejectedExecutionHandler 策略执行

        结束任务：
        1.从队列中取出一个正在等待的任务来执行
        2.如果有空闲进程，且超过 corePoolSize ，则根据 keepAliveTime 来销毁

         */

        Runnable runnable = () -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 7; i++) {
            executor.execute(runnable);
        }

        Thread.sleep(1000);


        System.out.println(executor.getActiveCount());

        for (int i = 0; i < 1; i++) {
            executor.execute(runnable);
            Thread.sleep(1000);
            System.out.println("====");
            System.out.println(executor.getActiveCount());
            System.out.println(executor.getPoolSize());
            System.out.println("====");
        }


//        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("over");

    }
}
