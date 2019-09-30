package com.github.fanshuzaizai.interview.jvm.error;

public class UnableToCreateNewNativeThread {

    /*
    unable to create new native thread
    无法创建本地线程
    这个错误和操作系统有关，linux默认每个进程最多创建1024个线程

    解决办法：
        1.检查代码是否真的需要创建这么多线程
        2.修改linux配置增驾上限

     */
    public static void main(String[] args) {

    }

}
