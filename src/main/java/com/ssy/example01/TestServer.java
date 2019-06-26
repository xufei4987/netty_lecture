package com.ssy.example01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {
    public static void main(String[] args) throws InterruptedException {

        //boss接收客户端的连接
        EventLoopGroup bossGrp = new NioEventLoopGroup();
        //worker处理客户端连接的请求
        EventLoopGroup workerGrp = new NioEventLoopGroup();
        try {
            //服务器启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGrp,workerGrp)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGrp.shutdownGracefully();
            workerGrp.shutdownGracefully();
        }


    }
}
