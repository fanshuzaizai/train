package com.github.fanshuzaizai.interview.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * lock 和 synchronized 都可以保证可见性
 *
 * @author Jzy.
 * @date 2019/8/15 10:56
 */
public class Visibility {

    int a = 3;

//    ReentrantLock lock = new ReentrantLock();

    public void change() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a = 10;
        }).start();
    }

    public boolean get() {
//        lock.lock();
        boolean b = a == 3;
//        lock.unlock();
        return b;
    }

    public static void main(String[] args) {

        Visibility visibility = new Visibility();
        visibility.change();

        while (visibility.get()) {

        }


        System.out.println("over");

    }

}
