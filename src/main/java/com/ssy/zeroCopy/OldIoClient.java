package com.ssy.zeroCopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/26 16:20
 **/
public class OldIoClient {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 8899);

        String fileName = "E:\\9-PRS7012\\01_PRS7012Web.7z";

        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCount = 0;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCount = inputStream.read(buffer)) != -1){
            total += readCount;
            dataOutputStream.write(buffer);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("发送总字节数："+total+",总耗时："+(endTime-startTime)+"ms");

        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
