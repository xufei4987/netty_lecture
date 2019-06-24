package com.ssy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/18 22:12
 **/
public class Test3 {
    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String s = bufferedReader.readLine();
        String[] strings = s.split(" ");
        String lastWord = strings[strings.length-1];
        System.out.println(lastWord.length());
    }
}
