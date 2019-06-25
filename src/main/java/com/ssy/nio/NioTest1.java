package com.ssy.nio;

import java.nio.IntBuffer;
import java.util.Random;

public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("capacity:" + intBuffer.capacity());
        for(int i = 0; i < 5; i++){
            Random random = new Random();
            int nextInt = random.nextInt(20);
            intBuffer.put(nextInt);
        }
        System.out.println("before flip limit:"+intBuffer.limit());
        System.out.println("before flip position:"+intBuffer.position());
        //写读转换
        intBuffer.flip();

        System.out.println("after flip limit:" + intBuffer.limit());
        System.out.println("after flip position:"+intBuffer.position());

        System.out.println("enter while loop");
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
            System.out.println("position:"+intBuffer.position());
            System.out.println("limit:"+intBuffer.limit());
            System.out.println("capacity:"+intBuffer.capacity());
        }
    }
}
