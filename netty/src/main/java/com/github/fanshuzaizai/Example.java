package com.github.fanshuzaizai;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Example {


    public static void main(String[] args) {

        ByteBuf hello = Unpooled.copiedBuffer("张三", StandardCharsets.UTF_8);
        System.out.println(hello);
        byte[] bytes = hello.array();
        System.out.println(Arrays.toString(bytes));

        StringDecoder stringDecoder = new StringDecoder();


        DefaultChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        channels.writeAndFlush(1);


    }

}
