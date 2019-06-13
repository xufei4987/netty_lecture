package com.ssy.example05;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @description webSocket服务端
 * @Author YouXu
 * @Date 2019/6/13 10:46
 **/
public class MyWebsocketServer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGrp = new NioEventLoopGroup();
        EventLoopGroup workerGrp = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGrp,workerGrp)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new MyWebsocketServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGrp.shutdownGracefully();
            workerGrp.shutdownGracefully();
        }
    }


}
