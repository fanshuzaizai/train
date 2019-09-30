package com.github.fanshuzaizai.algorithm;

import java.util.concurrent.*;

/**
 * @author JiangZY
 * @date Created in 2019/8/14
 */
public class Train {

    volatile static int a = 0;


    public static void main(String[] args) {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        executor.execute(()-> System.out.println("123"));


    }


}
