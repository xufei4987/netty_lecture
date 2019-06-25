package com.ssy.nio;

import java.nio.ByteBuffer;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/25 15:17
 **/
public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        byteBuffer.putChar('你');
        byteBuffer.putChar('我');
        byteBuffer.putChar('t');
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }
    }
}
