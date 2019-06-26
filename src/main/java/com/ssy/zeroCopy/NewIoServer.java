package com.ssy.zeroCopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/26 16:07
 **/
public class NewIoServer {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(8899));
        ByteBuffer readBuffer = ByteBuffer.allocate(10240);
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(true);
        int readCount = 0;
        while (readCount != -1){
            try {
                readCount = socketChannel.read(readBuffer);
            }catch (Exception e){
                e.printStackTrace();
            }
            readBuffer.clear();
        }
    }
}
