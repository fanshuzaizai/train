package com.github.fanshuzaizai.interview.concurrent;

public class ThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {
        InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("123");
        threadLocal.get();



        new Thread(()->{
            System.out.println(threadLocal.get());
        }).start();

        Thread.sleep(1000);





    }

}
