package com.ssy.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {
    public static void main(String[] args) throws Exception{

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        socketChannel.connect(new InetSocketAddress("127.0.0.1",8899));

        while (true){
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iter = selectionKeys.iterator();

            while (iter.hasNext()){
                SelectionKey selectionKey = iter.next();
                if(selectionKey.isConnectable()){
                    SocketChannel sChannel = (SocketChannel) selectionKey.channel();
                    if(sChannel.isConnectionPending()){
                        sChannel.finishConnect();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        byteBuffer.put((LocalDateTime.now() + " 连接成功").getBytes());
                        byteBuffer.flip();
                        sChannel.write(byteBuffer);

                        ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                        executorService.submit(()->{
                           while (true){
                               try {
                                   Scanner scanner = new Scanner(System.in);
                                   String next = scanner.next();
                                   byteBuffer.clear();
                                   byteBuffer.put(next.getBytes());
                                   byteBuffer.flip();
                                   sChannel.write(byteBuffer);
                               }catch (Exception ex){
                                   ex.printStackTrace();
                               }
                           }
                        });
                    }
                    sChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    SocketChannel sChannel = (SocketChannel) selectionKey.channel();

                    ByteBuffer readBuf = ByteBuffer.allocate(1024);

                    int count = sChannel.read(readBuf);

                    if(count>0){
                        String receivedMsg = new String(readBuf.array());
                        System.out.println(receivedMsg);
                    }
                }
                iter.remove();
            }
        }

    }
}
