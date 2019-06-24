package com.ssy.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @description
 * 题目描述
 * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 * 输入描述:
 * 输入一个int型整数
 * 输出描述:
 * 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
 * @Author YouXu
 * @Date 2019/6/20 17:41
 **/
public class Test11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long l = scanner.nextLong();
        String s = String.valueOf(l);
        Map<Object,Object> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(s.length() - i - 1);
            if(map.get(c) == null){
                map.put(c,0);
                System.out.print(c);
            }
        }
    }
}
