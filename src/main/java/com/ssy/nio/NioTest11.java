package com.ssy.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/25 19:37
 **/
public class NioTest11 {
    public static void main(String[] args) throws Exception {
        int[] ports = new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();

        for(int i = 0; i < ports.length; i++){
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(ports[i]));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口："+ports[i]);
        }

        while (true){
            int select = selector.select();
            System.out.println("select:"+select);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectedKeys="+selectionKeys);
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println("获得客户端连接： "+socketChannel);
                    iterator.remove();
                }else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                    int read = 0;
                    while(true){
                        byteBuffer.clear();
                        int r = socketChannel.read(byteBuffer);
                        if(r <= 0){
                            break;
                        }
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        read += r;
                    }
                    System.out.println("读取： "+read+" 来自于： "+socketChannel);
                    iterator.remove();
                }
            }
        }

    }
}
