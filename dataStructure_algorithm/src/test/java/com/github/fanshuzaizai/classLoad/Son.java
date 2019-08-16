package com.github.fanshuzaizai.classLoad;

/**
 * @author Jzy.
 * @date 2019/8/14 10:18
 */
public class Son extends Father {

    private int i = test();

    private static int j = method();

    static {
        System.out.println(6);
    }

    Son() {
        System.out.println(7);
    }

    {
        System.out.println(8);
    }

    public int test() {
        System.out.println(9);
        return 1;
    }

    public static int method() {
        System.out.println(10);
        return 1;
    }

    public static void main(String[] args) {
        Son s1 = new Son();

        System.out.println("===============");

        Son s2 = new Son();
    }

}
