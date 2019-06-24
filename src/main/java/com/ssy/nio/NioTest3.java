package com.ssy.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest3 {
    public static void main(String[] args) throws Exception {
        String str = "ni hao shi jie!";
        byte[] bytes = str.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        for(int i = 0; i < bytes.length; i++){
            byteBuffer.put(bytes[i]);
        }
        byteBuffer.flip();
        FileOutputStream fos = new FileOutputStream("2.txt");
        FileChannel channel = fos.getChannel();
        channel.write(byteBuffer);
        channel.close();
        fos.close();
    }
}
