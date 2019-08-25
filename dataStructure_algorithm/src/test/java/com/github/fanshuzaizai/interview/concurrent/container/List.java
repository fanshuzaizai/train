package com.github.fanshuzaizai.interview.concurrent.container;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Jzy.
 * @date 2019/8/15 16:24
 */
public class List {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> list = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }).start();
        }

//        CopyOnWriteArraySet<String> copy = new CopyOnWriteArraySet<>();
//        copy.add("12");
//
        Thread.sleep(1000);
        System.out.println(list);
        System.out.println("over");

        HashSet<String> strings = new HashSet<>();
        strings.add("12");

        Integer.parseInt("12");
        Integer.valueOf("12");

        java.util.List<Object> objects = Collections.synchronizedList(new ArrayList<>());

    }

}
