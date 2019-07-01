package com.ssy.nettyHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/7/1 17:31
 **/
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("encode invoked!");

        System.out.println(msg);

        out.writeLong(msg);
    }
}
