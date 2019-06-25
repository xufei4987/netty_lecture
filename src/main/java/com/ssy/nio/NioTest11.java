package com.ssy.nio;

import java.nio.channels.Selector;

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
    }
}
