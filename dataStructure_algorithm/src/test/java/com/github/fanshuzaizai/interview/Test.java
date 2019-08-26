package com.github.fanshuzaizai.interview;

import sun.misc.VM;

import java.lang.ref.SoftReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Jzy.
 * @date 2019/8/15 9:47
 */
public class Test {


    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("子线程开始");
            while (1 == 1) {
                if (Thread.interrupted()) {
                    System.out.println(true);
                }else {
                    System.out.println(false);
                }
            }

        });
        thread.start();

        thread.interrupt();
        if (true) {
            return;
        }

        //查询是否中断，不会产生任何负面影响
        boolean interrupted1 = Thread.currentThread().isInterrupted();

        //查询线程是否中断，如果是中断的，则清除中断标记
        boolean interrupted = Thread.interrupted();

        //中断线程，如果处于阻塞(wait,sleep,join)状态，会抛出异常
        Thread.currentThread().interrupt();

        LockSupport.park();
    }

}
