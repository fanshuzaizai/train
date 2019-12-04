package com.github.fanshuzaizai.nio.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author Jzy
 * @date 2019/12/4 22:08
 */
public class Client {

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(6666));
        socketChannel.configureBlocking(false);

        if (socketChannel.isConnected()) {
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = System.in.read(bytes)) != -1) {

                ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, 0, read);

                socketChannel.write(byteBuffer);
            }

        }


    }

}
