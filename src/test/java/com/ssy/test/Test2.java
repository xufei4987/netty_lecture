package com.ssy.test;


import com.sun.org.apache.xpath.internal.FoundIndex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/18 17:12
 **/
public class Test2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] strings = s.split(",");
        for (String s1 : strings){
            for (String s2 : strings){
                if(s1.equals(s2)){
                    continue;
                }
                if(Integer.parseInt(s1)%Integer.parseInt(s2) == 0){
                    System.out.print(s1 + " ");
                    break;
                }
            }
        }
    }
}
