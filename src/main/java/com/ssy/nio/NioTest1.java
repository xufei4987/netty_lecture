package com.ssy.nio;

import java.nio.IntBuffer;
import java.util.Random;

public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for(int i = 0; i < intBuffer.capacity(); i++){
            Random random = new Random();
            int nextInt = random.nextInt(20);
            intBuffer.put(nextInt);
        }
        //读写转换
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
