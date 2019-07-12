package com.github.fanshuzaizai.dataStructure_algorithm;

/**
 * @author Jzy.
 * @date 2019/7/4 15:47
 */
public class StopWatch {

    private long startTime;

    private String name;

    public StopWatch() {
        this.startTime = System.currentTimeMillis();
    }

    public StopWatch(String name) {
        this();
        this.name = name;
        System.out.println(name + "--开始");
    }

    public void over() {
        long time = System.currentTimeMillis() - startTime;
        System.out.println("总用时: " + time + " ms");
        System.out.println(name + "--结束");
    }
}
