package com.github.fanshuzaizai.netty.de_en_coder.simple.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable//必须保证线程安全
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client 建立连接");
        ctx.write(1);
        ctx.write(111);
        ctx.write(55);
         ctx.writeAndFlush(89);
        ctx.close();

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client..被断开了");
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
