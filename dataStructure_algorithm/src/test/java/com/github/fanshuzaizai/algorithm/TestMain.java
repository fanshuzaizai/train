package com.github.fanshuzaizai.algorithm;

import com.github.fanshuzaizai.algorithm.chapter1_base.Stack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * @author Jzy.
 * @date 2019/7/23 13:49
 */
@RunWith(JUnit4.class)
public class TestMain {


    @Test
    public void test1() {
        System.out.println(Integer.MAX_VALUE);//2147483647
        //2 ^31 = 2,147,483,648‬
        System.out.println(Double.valueOf(Math.pow(2, 31)).intValue());

        System.out.println(Integer.toBinaryString(-5));

        String s = "abcdefg";
        s.hashCode();
        System.out.println(Integer.toBinaryString(s.charAt(5)));

        HashMap<Object, Object> map = new HashMap<>();
        map.put(1, 1);
        map.hashCode();


        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();

        TreeMap<Object, Object> treeMap = new TreeMap<>();

    }

    @Test
    public void test2() {
        int a = 19;

        Integer aa = Integer.valueOf(19);

        Integer aa2 = Integer.valueOf(19);

        System.out.println(a == aa);
        System.out.println(aa.equals(a));
        System.out.println(aa2 == aa);
    }

    @Test
    public void test3() {
        BigDecimal aa = new BigDecimal("0.741");
        BigDecimal bb = new BigDecimal("1.366");
        BigDecimal cc = new BigDecimal("0.995");
        double a = 0.741;
        double b = 1.366;
        double c = 0.995;
        double v = -Math.log(a) + -Math.log(b) + -Math.log(c);
        System.out.println(v);

        System.out.println(aa.multiply(bb).multiply(cc).toPlainString());

    }
}
