package com.ssy.test;

import java.util.Scanner;

/**
 * @description
 * 密码要求:
 *
 *
 *
 * 1.长度超过8位
 *
 *
 *
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 *
 *
 *
 * 3.不能有相同长度超2的子串重复
 *
 *
 *
 * 说明:长度超过2的子串
 *
 * 输入描述:
 * 一组或多组长度超过2的子符串。每组占一行
 * 输出描述:
 * 如果符合要求输出：OK，否则输出NG
 * @Author YouXu
 * @Date 2019/6/21 23:01
 **/
public class Test20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String next = scanner.next();
            if(next.length()<=8){
                System.out.println("NG");
                continue;
            }
            int[] nType = new int[4];
            int nCount = 0;
            for(int i = 0; i < next.length(); i++){
                if(next.charAt(i) >= 'a' && next.charAt(i) <= 'z'){
                    if(nType[0] == 1){
                        continue;
                    }
                    nType[0] = 1;
                    nCount++;
                }else if(next.charAt(i) >= 'A' && next.charAt(i) <= 'Z'){
                    if(nType[1] == 1){
                        continue;
                    }
                    nType[1] = 1;
                    nCount++;
                }else if(next.charAt(i) >= '0' && next.charAt(i) <= '9'){
                    if(nType[2] == 1){
                        continue;
                    }
                    nType[2] = 1;
                    nCount++;
                }else{
                    if(nType[3] == 1){
                        continue;
                    }
                    nType[3] = 1;
                    nCount++;
                }
            }
            if(nCount < 3){
                System.out.println("NG");
                continue;
            }
            boolean bFind = false;
            for(int i = 0; i < next.length()-5; i++){
                String str1 = next.substring(i, i + 3);
                String str2 = next.substring(i+3);
                if(str2.contains(str1)){
                    bFind = true;
                    break;
                }
            }
            if(bFind){
                System.out.println("NG");
                continue;
            }
            System.out.println("OK");
        }
    }
}
