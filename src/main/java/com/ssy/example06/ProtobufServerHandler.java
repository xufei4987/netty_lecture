package com.ssy.example06;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Arrays;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/13 19:57
 **/
public class ProtobufServerHandler extends SimpleChannelInboundHandler<MyMsgInfo.Msg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMsgInfo.Msg msg) throws Exception {
        MyMsgInfo.Msg.DataType dataType = msg.getDataType();
        if(MyMsgInfo.Msg.DataType.PersonType == dataType){
            System.out.println(msg.getPerson());
        }else if(MyMsgInfo.Msg.DataType.DogType == dataType){
            System.out.println(msg.getDog());
        }else if (MyMsgInfo.Msg.DataType.CatType == dataType){
            System.out.println(msg.getCat());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
