package com.ssy.zeroCopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/26 16:43
 **/
public class NewIoClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        socketChannel.connect(new InetSocketAddress("localhost",8899));

        String fileName = "E:\\9-PRS7012\\01_PRS7012Web.7z";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();

        long total = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        long endTime = System.currentTimeMillis();

        fileChannel.close();

        System.out.println("发送总字节数："+total+",总耗时："+(endTime-startTime)+"ms");
    }
}
