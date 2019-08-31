package com.github.fanshuzaizai.interview.jvm.synchronize;

public class Example {

    /*
    每个对象都有一个隐藏的monitor对象，synchronized锁的其实是这个monitor对象
    synchronized锁是可重入的

    锁类型
        1.无锁


        2.偏向锁
             当一个线程持有了锁以后，这把锁就进入偏向模式，
             再次有线程尝试获取锁时，根据对比锁中的ThreadId来判断是否是已经获取锁的线程，
             根据里面的ThreadId判断当已经持有这个锁的线程再次获取锁时，不会再次执行任何同步操作，而是直接返回成功

        3.轻量级锁
            由 偏向锁 升级来，当第二个线程尝试获取一个偏向锁时，这把锁即升级为轻量级锁
            线程会通过CAS自旋，尝试一定次数（-XX:PreBlockSpin=），防止频繁的挂起/唤醒线程
            可以通过-XX:-UseBiasedLocking 关闭

        4.重量级锁
            当有线程尝试通过自旋获取锁一定次数失败后，会升级为重量级锁
            如果没有获取到锁，利用monitor对象，调用底层命令挂起线程，当锁被释放时，会唤醒所有线程来竞争锁


     */

    /**
     * 锁细化
     */
    public void test() {
        StringBuffer sb = new StringBuffer();
        //当前情况下，这个对象不可能被其他线程所持有，所以完全没有必要加锁，此时jvm会把锁去除掉
        sb.append("123");
        sb.append("456");
    }

    /**
     * 锁粗化
     */
    public static void test2() {
        StringBuffer sb = new StringBuffer();
        //这种情况会频繁的发生 抢锁、上锁、放锁 ，对系统资源造成极大损耗，此时jvm会扩大锁的范围，将整个循环都锁住，从而只上锁一次
        for (int i = 0; i < 100; i++) {
            sb.append(i);
        }
    }
}
