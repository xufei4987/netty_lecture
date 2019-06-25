package com.ssy.nio;

import java.nio.ByteBuffer;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/25 16:45
 **/
public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++){
            buffer.put((byte)i);
        }
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        readOnlyBuffer.put(0,(byte)1);
    }
}
