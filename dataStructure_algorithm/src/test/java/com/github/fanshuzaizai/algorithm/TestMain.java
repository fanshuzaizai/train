package com.github.fanshuzaizai.algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * @author Jzy.
 * @date 2019/7/23 13:49
 */
public class TestMain {

    public static void main(String[] args) {
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
}
