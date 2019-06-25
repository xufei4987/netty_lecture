package com.ssy.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/25 14:34
 **/
public class NioTest4 {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("input.txt");
        FileOutputStream fos = new FileOutputStream("output.txt");
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true){
            //position = 0 and limit = capacity
            byteBuffer.clear();
            int read = inChannel.read(byteBuffer);
            if(read == -1){
                break;
            }
            byteBuffer.flip();
            outChannel.write(byteBuffer);
        }
        inChannel.close();
        outChannel.close();
        fis.close();
        fos.close();
    }
}
