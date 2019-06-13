package com.ssy.example06;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ProtobufClientHandler extends SimpleChannelInboundHandler<MyMsgInfo.Msg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMsgInfo.Msg msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt = new Random().nextInt(3);
        MyMsgInfo.Msg msg = null;
        if(randomInt == 0){
            List<String> lessons = Arrays.asList("chinese", "english", "math", "computer");
            MyMsgInfo.Person person = MyMsgInfo.Person.newBuilder().setName("张三").setAge(18).setAddress("深圳").addAllLessons(lessons).build();
            msg = MyMsgInfo.Msg.newBuilder()
                    .setDataType(MyMsgInfo.Msg.DataType.PersonType)
                    .setPerson(person).build();
        }else if(randomInt == 1){
            MyMsgInfo.Dog dog = MyMsgInfo.Dog.newBuilder().setName("xiaogou").setAge(2).build();
            msg = MyMsgInfo.Msg.newBuilder()
                    .setDataType(MyMsgInfo.Msg.DataType.DogType)
                    .setDog(dog).build();
        }else if(randomInt ==2){
            MyMsgInfo.Cat cat = MyMsgInfo.Cat.newBuilder().setName("张三").setCity("深圳").build();
            msg = MyMsgInfo.Msg.newBuilder()
                    .setDataType(MyMsgInfo.Msg.DataType.CatType)
                    .setCat(cat).build();
        }
        ctx.channel().writeAndFlush(msg);
    }

}
