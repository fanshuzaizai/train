package com.github.fanshuzaizai.algorithm.chapter3;

/**
 * @author JiangZY
 * @date Created in 2019/7/21
 */
public class AAA {

    private int age = 10;

    public static void B(AAA a) {
        a.age = 15;
    }

    public static void main(String[] args) {
        AAA aaa = new AAA();
        B(aaa);
        System.out.println(aaa.age);
    }

}
