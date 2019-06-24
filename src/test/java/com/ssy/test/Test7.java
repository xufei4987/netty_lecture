package com.ssy.test;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @description 16转10进制
 * @Author YouXu
 * @Date 2019/6/20 14:09
 **/
public class Test7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> hexMap = new HashMap<>();
        hexMap.put("0",0);
        hexMap.put("1",1);
        hexMap.put("2",2);
        hexMap.put("3",3);
        hexMap.put("4",4);
        hexMap.put("5",5);
        hexMap.put("6",6);
        hexMap.put("7",7);
        hexMap.put("8",8);
        hexMap.put("9",9);
        hexMap.put("A",10);
        hexMap.put("B",11);
        hexMap.put("C",12);
        hexMap.put("D",13);
        hexMap.put("E",14);
        hexMap.put("F",15);
        while(scanner.hasNext()){
            String hex = scanner.next();
            String hexNum = hex.substring(2);
            int sum = 0;
            for(int i = 0; i < hexNum.length(); i++){
                Integer num = hexMap.get(String.valueOf(hexNum.charAt(i)));
                double pow = Math.pow(16, hexNum.length() - i - 1);
                sum += num*pow;
            }
            System.out.println(sum);
        }


    }
}
