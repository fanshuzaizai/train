package com.github.fanshuzaizai.interview;

import java.lang.reflect.Proxy;
import java.util.HashMap;

class NewTest2 {
    public static void main(String[] args) {
        String a = new String("abc");

        String b = "abc";

        System.out.println(a == b);

        HashMap<String, String> map = new HashMap<>(11);
        map.put("", "");
    }
}
