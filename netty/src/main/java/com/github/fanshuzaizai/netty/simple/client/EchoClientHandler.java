package com.github.fanshuzaizai.netty.simple.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

@ChannelHandler.Sharable//必须保证线程安全
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //Unpooled每次会都会创建一个新的ByteBuf对象
        ByteBuf byteBuf = Unpooled.copiedBuffer("Netty rocks!", StandardCharsets.UTF_8);

        /*
        ctx的写操作可能由其他线程出发，但最终由对应EventLoop的线程去执行：io.netty.util.concurrent.EventExecutor.inEventLoop()
            如果是负责EventLoop的线程，则立刻执行
            如果不是，则放入调度任务中，由EventLoop去队列中消费执行
            tip：如果是一个耗时长的任务，应该使用一个专门的EventExecutor，防止阻塞其他链接的操作
         */
        ctx.writeAndFlush(byteBuf);//从下一个ChannelHandler开始执行

        ctx.channel().writeAndFlush(null);//从头开始执行ChannelHandler
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("clien..被断开链接了");
        super.channelInactive(ctx);
    }

    /*
    SimpleChannel会在 channelRead0 方法之后释放自动释放msg，所以不应该在方法外保存使用msg的引用
    tip：释放方法使用到了 PhantomReference
     */
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
