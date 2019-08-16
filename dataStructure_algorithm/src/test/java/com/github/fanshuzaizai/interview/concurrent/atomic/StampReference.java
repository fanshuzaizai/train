package com.github.fanshuzaizai.interview.concurrent.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子引用+版本号
 *
 * @author Jzy.
 * @date 2019/8/15 11:18
 */
public class StampReference {

    static AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(0, 0);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            //值0->1 ，版本号0->1
            asr.compareAndSet(0, 1, 0, 1);
            //值1->0,版本号1->2
            asr.compareAndSet(1, 0, 1, 2);
        }).start();

        Thread.sleep(1000);

        boolean b = asr.compareAndSet(0, 1, 0, 1);
        System.out.println("主线程修改结果：" + b);//false
    }

}
