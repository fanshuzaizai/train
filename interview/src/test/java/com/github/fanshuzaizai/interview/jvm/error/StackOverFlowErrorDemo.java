package com.github.fanshuzaizai.interview.jvm.error;

public class StackOverFlowErrorDemo {

    /*
    栈内存溢出
     */
    public static void main(String[] args) {
        infiniteMethod();
    }

    private static void infiniteMethod() {
        infiniteMethod();
    }
}
