package com.ssy.nettyHandler3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new MyPersonDecoder())
                .addLast(new MyPersonEncoder())
                .addLast(new MyServerHandler());
    }
}
