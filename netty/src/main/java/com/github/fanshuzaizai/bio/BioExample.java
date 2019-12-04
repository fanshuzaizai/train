package com.github.fanshuzaizai.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioExample {

    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("启动server。。");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("链接到一个客户端");
            executorService.execute(() -> {
                try {
                    handle(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    private static void handle(Socket socket) throws IOException {
        int port = socket.getPort();
        System.out.println("port:" + port);

        byte[] bytes = new byte[1024];
        InputStream inputStream = socket.getInputStream();
        while (true) {
            int read = inputStream.read(bytes);
            if (read == -1) {
                break;
            }
            System.out.println("接收到新消息："+new String(bytes, 0, read, StandardCharsets.UTF_8)+" .over");
        }
    }

}
