package com.ssy.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @description
 * 题目描述
 * 给定n个字符串，请对n个字符串按照字典序排列。
 * @Author YouXu
 * @Date 2019/6/20 21:00
 **/
public class Test15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nCount = scanner.nextInt();
        ArrayList<String> sList = new ArrayList<>();
        while(nCount > 0){
            String word = scanner.next();
            sList.add(word);
            nCount--;
        }
        Collections.sort(sList);
        sList.forEach(System.out::println);
    }
}
