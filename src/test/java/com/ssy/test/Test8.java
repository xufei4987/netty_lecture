package com.ssy.test;

import java.util.Scanner;

/**
 * @description 输出一个整数的质数因子
 * @Author YouXu
 * @Date 2019/6/20 14:38
 **/
public class Test8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String next = scanner.next();
            Long ulDataInput = Long.valueOf(next);
            String result = getResult(ulDataInput.longValue());
            System.out.println(result);
        }
    }
    public static String getResult(long ulDataInput){
        int pnum = 2;
        String result = "";
        while(ulDataInput != 1){
            while(ulDataInput%pnum == 0){
                ulDataInput = ulDataInput/pnum;
                result += pnum + " ";
            }
            pnum++;
        }
        return result;
    }
}
