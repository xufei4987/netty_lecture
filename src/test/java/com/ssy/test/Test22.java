package com.ssy.test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/22 16:02
 **/
public class Test22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputs = input.split(" ");
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        x.add(0);
        y.add(0);
        for (int i = 0; i < inputs.length; i=i+2){
            x.add(Integer.parseInt(inputs[i]));
            y.add(Integer.parseInt(inputs[i+1]));
        }
        int len = x.size();
        double sum = 0;
        int index = 0;
        int m = 0;
        while(m < len - 1){
            int curX = x.get(index);
            int curY = y.get(index);
            x.remove(index);
            y.remove(index);
            double min = 0;
            for(int i = 0; i < x.size(); i++){
                if(i == 0){
                    min = Math.sqrt((x.get(i)-curX)*(x.get(i)-curX) + (y.get(i)-curY)*(y.get(i)-curY));
                    index = i;
                }
                if(min > Math.sqrt((x.get(i)-curX)*(x.get(i)-curX) + (y.get(i)-curY)*(y.get(i)-curY))){
                    min = Math.sqrt((x.get(i)-curX)*(x.get(i)-curX) + (y.get(i)-curY)*(y.get(i)-curY));
                    index = i;
                }
            }
            sum = sum + min;
            m++;
        }
        sum = sum + Math.sqrt(x.get(0)*x.get(0)+y.get(0)*y.get(0));
        System.out.println((int)sum);
    }



}
