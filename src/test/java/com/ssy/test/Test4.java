package com.ssy.test;

import java.util.Scanner;

/**
 * @description 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，
 * 然后输出输入字符串中含有该字符的个数。不区分大小写
 * 输入描述:
 * 输入一个有字母和数字以及空格组成的字符串，和一个字符。
 * 输出描述:
 * 输出输入字符串中含有该字符的个数。
 * @Author YouXu
 * @Date 2019/6/18 22:23
 **/
public class Test4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next().toLowerCase();
        String s2 = scanner.next().toLowerCase();
        int nCount = 0;
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i) == s2.charAt(0)){
                nCount++;
            }
        }
        System.out.println(nCount);
    }
}
