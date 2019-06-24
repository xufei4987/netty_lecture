package com.ssy.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/21 17:34
 **/
public class Test19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,Integer> map = new HashMap<>();
        while(scanner.hasNext()){
            String path = scanner.next();
            int row = scanner.nextInt();
            if(map.get(path+" "+row) == null){
                map.put(path+" "+row,1);
            }else{
                map.put(path+" "+row,map.get(path+" "+row)+1);
            }
        }
        map.forEach((key,value)->{
            String[] s = key.split(" ");
            String fileName = s[0].substring(s[0].lastIndexOf("\\")+1);
            if(fileName.length()>16){
                fileName = s[0].substring(s[0].length() - 16);
            }
            System.out.println(fileName+" "+s[1]+" "+value);
        });
    }
}
