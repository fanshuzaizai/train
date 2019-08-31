package com.github.fanshuzaizai.interview;

/**
 * @author Jzy.
 * @date 2019/8/15 9:47
 */
public class Test {

    public int a = 10;

    public static void abc(){
        String str1 = "123";  // 字面两直接赋值
        String str2 = new String("123"); // 调用字符串构造方法生成对象
        String str3 = str1 + str2;
        final String str4 = "123";
        final String str5 = "456";
        String  str6 = str4 + str5;

    }

    public static void main(String[] args) throws InterruptedException {
        // #1
        String str1 = new String("1") + new String("1");
        str1.intern();
        String str2 = "11";
// 不知道为什么不是指向同一个引用
        System.out.println(str1 == str2); //  false，
// 两个变量的hashCode也是相等的
        System.out.println(str1.hashCode() == str2.hashCode()); // true
        System.out.println(str1.equals(str2)); // true


    }

}
