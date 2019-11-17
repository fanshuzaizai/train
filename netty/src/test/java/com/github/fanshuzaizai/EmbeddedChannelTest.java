package com.github.fanshuzaizai;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class EmbeddedChannelTest {

    @Test
    public void testFramesDecoded() {
        ByteBuf origin = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            origin.writeByte(i);
        }
        ByteBuf input = origin.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3)); // write bytes

        //将数据写入 channel 入栈
        assertTrue(channel.writeInbound(input.retain()));
        assertTrue(channel.finish());
        //从channel 的 入栈读取数据
        ByteBuf read = channel.readInbound();
        assertEquals(origin.readSlice(3), read);
        read.release();
        read = channel.readInbound();
        assertEquals(origin.readSlice(3), read);
        read.release();
        read = channel.readInbound();
        assertEquals(origin.readSlice(3), read);
        read.release();
        assertNull(channel.readInbound());
        origin.release();
    }


}
