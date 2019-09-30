package com.github.fanshuzaizai.interview.jvm.memory;

public class Cat {

    private int age = 13;

    String name = "123";

    public String getName() {
        return name;
    }

    /*
    public int addAge(int);
    descriptor: (I)I
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=2, args_size=2
         0: aload_0
         1: getfield      #2                  // Field age:I
         4: iload_1
         5: iadd
         6: ireturn
     LineNumberTable:
        line 14: 0
     LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       7     0  this   Lcom/github/fanshuzaizai/interview/jvm/memory/Cat;
            0       7     1 addNum   I

     1.aload_0 从本地变量表把第一个变量（this）压入栈中，此时栈中有1个数据
     2.getfield 将 this 出栈，并将属性 age 压入栈种，此时栈中有1个数据
     3.iload_1 将 本地变量表 中 下标1 的值 入栈，此时栈中有2个数据
     4.iadd 将2个栈中数字 出战，相加，并将结果入栈，此时栈中有1个数据
     5.ireturn 将 栈中 数字 返回
     */
    public int addAge(int addNum) {
        return age + addNum;
    }

    public int add35() {
        return age + 35;
    }

    public void sayLoop() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }

    public void stringPool() {
        String ttt = "aabbcc";
        String tttxxx = "aabbcc".intern();
        String xxx = new String("qqwwee");

    }

}
