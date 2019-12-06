package com.github.fanshuzaizai.netty.de_en_coder.simple.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

@ChannelHandler.Sharable
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(8000).start();
    }


    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();

        //EpollEventLoopGroup 比 NioEventLoopGroup 更快，只能在linux上使用
        EventLoopGroup parentGroup = new NioEventLoopGroup();//用来接受来自客户端的链接，并创建channel
        EventLoopGroup childGroup = new NioEventLoopGroup();//用来和channel进行通信
        try {

            ServerBootstrap b = new ServerBootstrap();//服务端引导类
            b.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
//                    .option()
                    .localAddress(new InetSocketAddress(port))
                    //当一个新的连接 被接受时，一个新的子 Channel 将会被创建，
                    // 而 ChannelInitializer 将会把一个你的 EchoServerHandler 的实例添加到该 Channel 的 ChannelPipeline 中
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new ToIntegerDecoder(), serverHandler);

                        }
                    });

            AttributeKey<Object> id = AttributeKey.valueOf("id");
            b.attr(id, 123);


            //绑定了服务器，并等待绑定完成
            ChannelFuture f = b.bind().sync();
            System.out.println("22222");

            ChannelFuture channelFuture = f.channel().closeFuture();//关闭事件
            channelFuture.sync();//会阻塞直到关闭事件complete，所以正常运行中不会往下走
            System.out.println("33333");

        } finally {
            parentGroup.shutdownGracefully().sync();
            childGroup.shutdownGracefully().sync();
        }
    }
}