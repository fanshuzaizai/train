package com.github.fanshuzaizai.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Jiangzy
 * @date 2019/12/6
 */
public class Server {

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    private Map<Integer, SocketChannel> map = new HashMap<>();

    public Server(int port) throws Exception {
        init(port);
    }

    private void init(int port) throws Exception {
        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    private void listen(long mils) throws Exception {
        int select = selector.select(mils);
        if (select == 0) {
//            System.out.println("没有操作");
            return;
        }

        Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();

            if (key.isAcceptable()) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
                map.put(socketChannel.hashCode(), socketChannel);
                System.out.println("服务端 建立一个新连接。hash：" + socketChannel.hashCode());
            }

            if (key.isReadable()) {
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = channel.read(byteBuffer);
                byteBuffer.flip();
                List<SocketChannel> target = map.entrySet().stream().filter(e -> e.getKey() != channel.hashCode()).map(Map.Entry::getValue).collect(Collectors.toList());
                List<Integer> hashList = target.stream().map(Object::hashCode).collect(Collectors.toList());
                System.out.println(String.format("收到 %d 客户端的消息：%s，向其他客户端发送： %s", channel.hashCode(), new String(byteBuffer.array(), 0, read), hashList));
                target.forEach(e -> {
                    try {
                        e.write(byteBuffer);
                        //复位，可以重新读取
                        byteBuffer.rewind();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            }

            keyIterator.remove();
        }

    }


    public static void main(String[] args) throws Exception {

        Server server = new Server(9999);
        while (true) {
            server.listen(2000);
        }


    }

}
