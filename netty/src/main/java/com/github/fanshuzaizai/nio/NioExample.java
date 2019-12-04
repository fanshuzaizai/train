package com.github.fanshuzaizai.nio;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author Jzy
 * @date 2019/11/30 10:49
 */
public class NioExample {

    public static void main(String[] args) throws IOException {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        intBuffer.hasRemaining();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        ByteBuffer byteBuffer1 = byteBuffer.asReadOnlyBuffer();
        Buffer flip = byteBuffer.flip();


        byteBuffer.putInt(12);
        byteBuffer.putLong(33L);
        byteBuffer.putShort((short) 44);


        ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect(1024);


        AbstractSelector selector = SelectorProvider.provider().openSelector();

        selector.wakeup();

        selector.select();


        ServerSocketChannel serverSocketChannel = SelectorProvider.provider().openServerSocketChannel();



    }

}
