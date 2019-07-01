package com.ssy.nettyHandler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        System.out.println("客户端接收到的消息：");
        System.out.println("长度："+msg.getLength());
        System.out.println("内容："+new String(msg.getContent(),CharsetUtil.UTF_8));

        System.out.println("客户端接收到的消息数量：" + (++this.count));
        System.out.println();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++){
            String msg = "sent from client ";
            byte[] bytes = msg.getBytes(CharsetUtil.UTF_8);
            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(msg.length());
            personProtocol.setContent(bytes);
            ctx.writeAndFlush(personProtocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
