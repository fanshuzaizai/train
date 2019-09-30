package com.github.fanshuzaizai.interview.concurrent.volatile_;

/**
 * 可见性
 * @author Jzy.
 * @date 2019/8/15 10:56
 */
public class Visibility {

    /*volatile*/ static int a = 3;

    public static void main(String[] args) {

        /*
        新线程从主内存拷贝一份数据，过3s进行修改
         */
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a = 10;
        }).start();

        /*
        当前线程从主内存拷贝一份数据
        不加 volatile ，则收不到通知其他线程已将其修改的通知，就一直死循环
         */
        while (a == 3) {

        }
        System.out.println(a);

    }

}
