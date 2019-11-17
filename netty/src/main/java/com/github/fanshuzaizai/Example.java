package com.github.fanshuzaizai;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static io.netty.util.ByteProcessor.FIND_NUL;

public class Example {


    public static void main(String[] args) {

        ReferenceCountUtil.release(null);


        ByteBuf byteBuf = Unpooled.copiedBuffer("Netty rocks!",
                CharsetUtil.UTF_8);




        int capacity = byteBuf.capacity();
        int i = byteBuf.maxCapacity();

        byteBuf.duplicate();
        ByteBuf copy = byteBuf.copy();
        byteBuf.slice();

        byte aByte = byteBuf.getByte(1);
        ByteProcessor findNul = FIND_NUL;


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        },1000);

        ScheduledThreadPoolExecutor scheduledExecutorService = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);

        scheduledExecutorService.schedule(()->{

        },1000, TimeUnit.MILLISECONDS);


    }

}
