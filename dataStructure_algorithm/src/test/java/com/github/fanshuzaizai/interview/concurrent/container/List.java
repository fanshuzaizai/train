package com.github.fanshuzaizai.interview.concurrent.container;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Jzy.
 * @date 2019/8/15 16:24
 */
public class List {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> list = new ArrayList<>();


        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
            }).start();
        }

        CopyOnWriteArraySet<String> copy = new CopyOnWriteArraySet<>();
        copy.add("12");

        Thread.sleep(1000);
        System.out.println(list.size());
        System.out.println("over");
    }

}
