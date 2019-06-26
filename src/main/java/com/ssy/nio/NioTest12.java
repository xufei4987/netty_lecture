package com.ssy.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @description
 * ASCII:7bit表示一个字符 ，可以表示128个字符
 * ISO-8859-1:8bit表示一个字符 ，可以表示256个字符
 * gb2312:2byte(16bit)表示一个汉字字符
 * gbk:gb2312的超集
 * gbk18030:gbk的超集
 * unicode:2byte(16bit)表示一个字符,可以表示全球所有字符
 * utf-8(unicode transform format):是unicode的一种实现方式,变长直接表示形式（1-6个直接，一般汉字用3个直接）
 *      BOM（Byte order mark）:有带BOM和不带BOM 带BOM文件头部有标记
 * @Author YouXu
 * @Date 2019/6/26 10:17
 **/
public class NioTest12 {
    public static void main(String[] args) throws Exception{
        String inputFile = "inFile.txt";
        String outputFile = "outFile.txt";

        RandomAccessFile inRandomAccessFile = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outRandomAccessFile = new RandomAccessFile(outputFile, "rw");

        long length = new File(inputFile).length();

        FileChannel inChannel = inRandomAccessFile.getChannel();
        FileChannel outChannel = outRandomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);

        Charset charset = Charset.forName("utf-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();
        CharBuffer decode = decoder.decode(mappedByteBuffer);
        ByteBuffer encode = encoder.encode(decode);

        outChannel.write(encode);

        inChannel.close();
        inRandomAccessFile.close();
        outChannel.close();
        outRandomAccessFile.close();
    }
}
