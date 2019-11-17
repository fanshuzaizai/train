package com.github.fanshuzaizai.interview.threadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPool extends ThreadPoolExecutor {

    public CustomThreadPool(int nThreads) {
        super(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    @Override
    public void execute(Runnable command) {
        String s = MessageContext.get();
        Runnable a = () -> {
            MessageContext.set(s);
            command.run();
        };

        super.execute(a);
    }

}
