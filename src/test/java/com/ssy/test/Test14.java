package com.ssy.test;

import java.util.Scanner;
import java.util.Stack;

/**
 * @description
 * 题目描述
 * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
 * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 **/
public class Test14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] ss = s.split(" ");
        Stack<Object> stack = new Stack<>();
        for(int i = 0; i < ss.length; i++){
            stack.push(ss[i]);
        }
        for (int i = 0; i < ss.length; i++){
            if(i == ss.length - 1){
                System.out.print(stack.pop());
            }else {
                System.out.print(stack.pop() + " ");
            }
        }
    }
}
