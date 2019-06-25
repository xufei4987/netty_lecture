package com.ssy.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @description Scattering(分散) 和 Gathering(聚集)
 * @Author YouXu
 * @Date 2019/6/25 17:25
 **/
public class NioTest10 {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(inetSocketAddress);
        SocketChannel socketChannel = serverSocketChannel.accept();

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);

        while (true){
            long bytesRead = 0;
            while(bytesRead < messageLength){
                long r = socketChannel.read(byteBuffers);
                bytesRead += r;

                System.out.println("bytesRead:"+bytesRead);

                Arrays.asList(byteBuffers).stream()
                        .map(buffer -> "limit:"+buffer.limit()+" position:"+buffer.position())
                        .forEach(System.out::println);
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());

            long bytesWrite = 0;
            while (bytesWrite < messageLength){
                long r = socketChannel.write(byteBuffers);
                bytesWrite += r;

            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

        }

    }
}
