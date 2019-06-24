package com.ssy.test;

import java.util.Scanner;

/**
 * @description
 * 题目描述:
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * 输入描述:
 * 连续输入字符串(输入2次,每个字符串长度小于100)
 * 输出描述:
 * 输出到长度为8的新字符串数组
 * @Author YouXu
 * @Date 2019/6/18 23:08
 **/
public class Test6 {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String s1 = scanner.next();
//        int mod1 = s1.length() % 8;
//        while(mod1 != 0){
//            s1 += "0";
//            mod1 = s1.length() % 8;
//        }
//
//        int nCount1 = s1.length() / 8;
//
//        String s2 = scanner.next();
//        int mod2 = s2.length() % 8;
//        while(mod2 != 0){
//            s2 += "0";
//            mod2 = s2.length() % 8;
//        }
//        int nCount2 = s2.length() / 8;
//        String[] strings = new String[nCount1 + nCount2];
//        int n = 0;
//        while (n < nCount1){
//            strings[n] = s1.substring(8*n,8*(n+1));
//            n++;
//        }
//        int m = 0;
//        while (m < nCount2){
//            strings[n+m] = s2.substring(8*m,8*(m+1));
//            m++;
//        }
//        for (String s : strings) {
//            System.out.println(s);
//        }
//
//    }
public static void main(String[] args) {
    Scanner scanner=new Scanner(System.in);
    while(scanner.hasNextLine()){
        String s=scanner.nextLine();
        split(s);
    }
}

    public static void split(String s){
        while(s.length()>=8){
            System.out.println(s.substring(0, 8));
            s=s.substring(8);
        }
        if(s.length()<8&&s.length()>0){
            s=s+"00000000";
            System.out.println(s.substring(0, 8));
        }
    }
}
