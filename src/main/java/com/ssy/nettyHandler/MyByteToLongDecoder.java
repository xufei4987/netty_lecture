package com.ssy.nettyHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.List;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/7/1 17:20
 **/
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decoder invoked!");
        System.out.println(in.readableBytes());
        if(in.readableBytes() >= 8){
            out.add(in.readLong());
        }
    }
}
