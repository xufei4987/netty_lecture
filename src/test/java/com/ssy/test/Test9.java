package com.ssy.test;

import java.util.Scanner;

/**
 * @description 近似值
 * @Author YouXu
 * @Date 2019/6/20 17:14
 **/
public class Test9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double nextDouble = scanner.nextDouble();
        int i = (int) nextDouble;
        i = nextDouble - i >= 0.5 ? i + 1 : i;
        System.out.println(i);
    }
}
