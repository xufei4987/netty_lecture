package com.ssy.nettySrcAnalysis;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufTest01 {

    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0; i < buffer.capacity(); i++){
            buffer.writeByte(i);
        }

        for (int i = 0; i < buffer.capacity(); i++){
            System.out.println(buffer.getByte(i));
        }
    }
}
