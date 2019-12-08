package com.github.fanshuzaizai.nio.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Jzy
 * @date 2019/12/4 21:30
 */
public class Server {

    public static void main(String[] args) throws IOException {

        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator<Object> iterator = arrayList.iterator();


        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);
//        serverSocketChannel.socket().bind();
        serverSocketChannel.bind(new InetSocketAddress(6666));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int select = selector.select(1000);
            if (select == 0) {
                System.out.println("服务端还未接收到数据。。");
                continue;
            }

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("服务端 建立连接");
                }

                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int read = channel.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array(), 0, read));
                }

                keyIterator.remove();
            }


        }


    }

}
