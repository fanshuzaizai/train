package com.github.fanshuzaizai.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Jzy.
 * @date 2019/8/15 9:47
 */
public class Test {


    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        list.add("12");
        list.add("12");
        list.add("12");

        Vector<String> vector = new Vector<>();
        vector.add("12");
        CopyOnWriteArrayList<String> copy = new CopyOnWriteArrayList<>();
        copy.add("12");
        copy.addIfAbsent("12");
        copy.get(12);

        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        synchronizedList.add("12");
    }

}
