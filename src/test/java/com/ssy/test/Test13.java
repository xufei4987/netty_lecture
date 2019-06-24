package com.ssy.test;

import java.util.Scanner;
import java.util.Stack;

/**
 * @description
 * 题目描述
 * 描述：
 * 输入一个整数，将这个整数以字符串的形式逆序输出
 * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
 *
 * 输入描述:
 * 输入一个int整数
 * 输出描述:
 * 将这个整数以字符串的形式逆序输出
 * @Author YouXu
 * @Date 2019/6/20 18:14
 **/
public class Test13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long l = scanner.nextLong();
        String s = String.valueOf(l);
        Stack<Object> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i++){
            stack.push(chars[i]);
        }
        for (int i = 0; i < chars.length; i++){
            System.out.print(stack.pop());
        }
    }
}
