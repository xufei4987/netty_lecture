package com.ssy.zeroCopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/26 14:58
 **/
public class OldIoServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            try {
                byte[] byteArr = new byte[4096];
                while (true) {

                    int read = dataInputStream.read(byteArr, 0, byteArr.length);

                    if(read == -1){
                        break;
                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
