package com.github.fanshuzaizai.netty.de_en_coder.simple.server;

import io.netty.channel.*;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("Server received: " + msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().eventLoop().execute(null);
        System.out.println("Server ..被断开了");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("Server read complete ");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}