package com.github.fanshuzaizai.interview.classLoad;

public class Cat {

    static {
        System.out.println("static....block initiated");
    }

    private final int age;

    public Cat() {
        this.age = 44;
    }

    public Cat(int age) {
        this.age = age;
    }

}
