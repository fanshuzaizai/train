package com.github.fanshuzaizai.interview.threadPool;

import java.util.concurrent.*;

public class AAA {

    public static void main(String[] args) {
        MessageContext.set("123");
        CustomThreadPool customThreadPool = new CustomThreadPool(8);
        customThreadPool.execute(() -> {
            System.out.println(MessageContext.get());
        });
        customThreadPool.shutdown();
    }

}
