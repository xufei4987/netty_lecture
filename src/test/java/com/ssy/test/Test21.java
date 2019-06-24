package com.ssy.test;

import java.util.Scanner;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/21 23:32
 **/
public class Test21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String originalPwd = scanner.next();
        char[] destPwd = new char[originalPwd.length()];
        for(int i = 0; i < originalPwd.length(); i++){
            if("abc".indexOf(originalPwd.charAt(i))!=-1){
                destPwd[i] = '2';
            }else if("def".indexOf(originalPwd.charAt(i))!=-1){
                destPwd[i] = '3';
            }else if("ghi".indexOf(originalPwd.charAt(i))!=-1){
                destPwd[i] = '4';
            }else if("jkl".indexOf(originalPwd.charAt(i))!=-1){
                destPwd[i] = '5';
            }else if("mno".indexOf(originalPwd.charAt(i))!=-1){
                destPwd[i] = '6';
            }else if("pqrs".indexOf(originalPwd.charAt(i))!=-1){
                destPwd[i] = '7';
            }else if("tuv".indexOf(originalPwd.charAt(i))!=-1){
                destPwd[i] = '8';
            }else if("wxyz".indexOf(originalPwd.charAt(i))!=-1){
                destPwd[i] = '9';
            }else if(originalPwd.charAt(i) >= 'A' && originalPwd.charAt(i) <= 'Z'){
                destPwd[i]=(char)(originalPwd.charAt(i) + 33);
                if(originalPwd.charAt(i) == 'Z'){
                    destPwd[i] = 'a';
                }
            }else{
                destPwd[i] = originalPwd.charAt(i);
            }

        }
        String s = String.valueOf(destPwd);
        System.out.println(s);
    }
}
