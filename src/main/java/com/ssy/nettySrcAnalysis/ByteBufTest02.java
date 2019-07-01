package com.ssy.nettySrcAnalysis;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/7/1 10:57
 **/
public class ByteBufTest02 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("张hello world", CharsetUtil.UTF_8);

        if(byteBuf.hasArray()){
            byte[] array = byteBuf.array();
            String str = new String(array, Charset.forName("utf-8"));
            System.out.println(str);
            System.out.println(byteBuf);
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity()); // 3*11 编码的最大字节数*数据长度
            System.out.println(byteBuf.readableBytes()); //widx - ridx
            System.out.println("-----------------------");
            for (int i = 0; i < byteBuf.readableBytes(); i++){
                System.out.println( (char) byteBuf.getByte(i) );
            }
            System.out.println("-----------------------");
            System.out.println(byteBuf.getCharSequence(0, 3, Charset.forName("utf-8")));
            System.out.println(byteBuf.getCharSequence(4, 6, Charset.forName("utf-8")));
        }
    }
}
