package com.ssy.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws Exception{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            int select = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                SocketChannel socketChannel = null;
                try {
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel ssChannel = (ServerSocketChannel) selectionKey.channel();
                        socketChannel = ssChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        String key = "[" + UUID.randomUUID().toString() + "]";
                        clientMap.put(key, socketChannel);
                    } else if (selectionKey.isReadable()) {
                        socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer readBuf = ByteBuffer.allocate(1024);
                        int count = socketChannel.read(readBuf);
                        if (count > 0) {
                            readBuf.flip();
                            Charset charset = Charset.forName("utf-8");
                            String receivedMsg = String.valueOf(charset.decode(readBuf).array());
                            System.out.println(socketChannel + ":" + receivedMsg);
                            String sendKey = null;
                            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                if (entry.getValue() == socketChannel) {
                                    sendKey = entry.getKey();
                                }
                            }
                            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                SocketChannel sc = entry.getValue();
                                ByteBuffer writeBuf = ByteBuffer.allocate(1024);
                                writeBuf.put((entry.getKey() + ": " + receivedMsg + "\n").getBytes());
                                writeBuf.flip();
                                sc.write(writeBuf);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if(socketChannel != null){
                        socketChannel.close();
                    }
                }finally {
                    iterator.remove();
                }
            }

        }
    }
}
