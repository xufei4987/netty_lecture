package com.ssy.nettyHandler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        System.out.println("服务器接收到的数据：");
        System.out.println("长度："+msg.getLength());
        System.out.println("内容："+msg.getContent());

        System.out.println("服务器接收到的消息数量：" + (++this.count));
        System.out.println();

        String responseMsg = UUID.randomUUID().toString();
        int responseLength = responseMsg.length();
        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(responseLength);
        personProtocol.setContent(responseMsg.getBytes(CharsetUtil.UTF_8));

        ctx.writeAndFlush(personProtocol);
    }
}
