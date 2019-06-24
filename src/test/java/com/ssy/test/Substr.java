package com.ssy.test;


import java.io.UnsupportedEncodingException;

/**
 * @description 根据字符数截取字符串，排除掉数字，汉字不能被截断
 * 如：str="天天abc123" byteNum=3 输出：天天
 * 如：str="天天abc123" byteNum=4 输出：天天
 * 如：str="天天abc123" byteNum=5 输出：天天a
 * 如：str="天天abc123" byteNum=6 输出：天天ab
 * 如：str="天天abc123" byteNum=7 输出：天天abc
 * 如：str="天天abc123" byteNum=8 输出：天天abc
 * @Author YouXu
 * @Date 2019/6/18 15:43
 **/
public class Substr {

    public static void substr(String str, int byteNum) throws UnsupportedEncodingException {
        byte[] gbks = str.getBytes("GBK");
        System.out.println(gbks.length);
        byte[] distGbks = new byte[gbks.length];
        int i = 0;
        for (int j = 0; j < gbks.length; j++) {
            System.out.println(gbks[j]);
            if(gbks[j] >= '0' && gbks[j] <= '9'){
                continue;
            }else if(gbks[j] > '9' && gbks[j] <= 'z'){
                distGbks[i++] = gbks[j];
            }else{
                distGbks[i++] = gbks[j];
                distGbks[i++] = gbks[++j];
            }
            if(i >= byteNum){
                break;
            }
        }
        String gbk = new String(distGbks, "GBK");
        System.out.println(gbk);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Substr.substr("天天HUA111",3);
    }
}
