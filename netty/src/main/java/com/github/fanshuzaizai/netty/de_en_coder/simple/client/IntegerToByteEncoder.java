package com.github.fanshuzaizai.netty.de_en_coder.simple.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class IntegerToByteEncoder extends MessageToByteEncoder<Integer> {

    @Override
    public void encode(ChannelHandlerContext ctx, Integer msg, ByteBuf out) throws Exception {
        out.writeInt(msg);
    }
}