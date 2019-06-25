package com.ssy.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description 内存映射文件，通过操作内存改变文件内容
 * @Author YouXu
 * @Date 2019/6/25 17:12
 **/
public class NioTest9 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0,(byte) 'a');
        mappedByteBuffer.put(1,(byte) 'b');
        channel.close();
        randomAccessFile.close();
    }
}
