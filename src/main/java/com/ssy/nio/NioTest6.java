package com.ssy.nio;

import java.nio.ByteBuffer;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/25 16:26
 **/
public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < byteBuffer.capacity(); i++){
            byteBuffer.put((byte)i);
        }
        byteBuffer.position(2);
        byteBuffer.limit(6);
        //获取[2,6)的一个共享子缓冲区
        ByteBuffer subBuffer = byteBuffer.slice();
        //对子缓冲区的操作也将反应到主缓冲区中
        for(int i = 0; i < subBuffer.capacity(); i++){
            byte b = subBuffer.get(i);
            b *= 2;
            subBuffer.put(i,b);
        }

        byteBuffer.clear();
        System.out.println("byteBuffer.limit="+byteBuffer.limit());
        System.out.println("byteBuffer.position="+byteBuffer.position());
        System.out.println("subBuffer.limit="+subBuffer.limit());
        System.out.println("subBuffer.position="+subBuffer.position());

        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }
    }
}
