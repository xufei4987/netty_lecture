package com.ssy.test;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * @description
 * 题目描述
 * 数据表记录包含表索引和数值，请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 * 输入描述:
 * 先输入键值对的个数
 * 然后输入成对的index和value值，以空格隔开
 * 输出描述:
 * 输出合并后的键值对（多行）
 * @Author YouXu
 * @Date 2019/6/20 17:21
 **/
public class Test10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nCount = scanner.nextInt();
        TreeMap<Integer, Integer> iiMap = new TreeMap<>();
        while(nCount > 0){
            String key = scanner.next();
            String value = scanner.next();
            if(iiMap.get(Integer.valueOf(key)) == null){
                iiMap.put(Integer.valueOf(key),Integer.valueOf(value));
            }else{
                iiMap.put(Integer.valueOf(key),iiMap.get(Integer.valueOf(key)) + Integer.valueOf(value));
            }
            nCount--;
        }
        iiMap.forEach((key,value)->{
            System.out.println(key+" "+value);
        });
    }
}
