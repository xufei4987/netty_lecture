package com.ssy.test;

import java.util.Scanner;

/**
 * @description
 * 题目描述
 * 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)。不在范围内的不作统计。
 * 输入描述:
 * 输入N个字符，字符在ACSII码范围内。
 * 输出描述:
 * 输出范围在(0~127)字符的个数。
 * @Author YouXu
 * @Date 2019/6/20 17:58
 **/
public class Test12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        int[] ints = new int[128];
        int nCount = 0;
        for(int i = 0; i < next.length(); i++){
            char c = next.charAt(i);
            if(c>=0 && c<=127){
                ints[c] = 1;
            }
        }
        for (int id:ints) {
            if(id == 1){
                nCount++;
            }
        }
        System.out.println(nCount);
    }
}
