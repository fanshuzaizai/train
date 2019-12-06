package com.github.fanshuzaizai.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author Jzy
 * @date 2019/12/4 22:08
 */
public class Client {

    private Selector selector;

    private SocketChannel socketChannel;

    public Client(int port) throws Exception {
        init(port);
    }

    private void init(int port) throws Exception {
        socketChannel = SocketChannel.open(new InetSocketAddress(port));
        socketChannel.configureBlocking(false);

        if (socketChannel.isConnected()) {
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);
        }

        new Thread(() -> {
            while (true) {
                try {
                    listen(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void listen(long mils) throws Exception {
        int select = selector.select(mils);
        if (select == 0) {
            return;
        }

        Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            if (key.isReadable()) {
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = channel.read(byteBuffer);
                System.out.println("收到消息：" + new String(byteBuffer.array(), 0, read));
            }
        }
    }

    private void send() throws IOException {
        int read = 0;
        byte[] bytes = new byte[1024];
        while ((read = System.in.read(bytes)) != -1) {
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, 0, read);

            socketChannel.write(byteBuffer);
        }
    }

    public static void main(String[] args) throws Exception {

        Client client = new Client(9999);
        client.send();


    }

}
