package com.ssy.test;

import java.util.Scanner;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/20 21:20
 **/
public class Test16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int nCount = 0;
        String binaryString = Integer.toBinaryString(number);
        for(int i = 0; i < binaryString.length(); i++){
            if(binaryString.charAt(i) == '1'){
                nCount++;
            }
        }
        System.out.println(nCount);
    }
}
