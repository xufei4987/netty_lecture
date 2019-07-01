package com.ssy.nettySrcAnalysis;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/7/1 11:50
 **/
public class ByteBufTest03 {

    public static void main(String[] args) {
        CompositeByteBuf byteBufs = Unpooled.compositeBuffer();

        ByteBuf heapBuf = Unpooled.buffer(10);
        ByteBuf directBuf = Unpooled.directBuffer(8);

        byteBufs.addComponents(heapBuf,directBuf);
//        byteBufs.removeComponent(0);

//        Iterator<ByteBuf> iterator = byteBufs.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

        byteBufs.forEach(System.out::println);

    }

}

