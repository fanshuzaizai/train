package com.github.fanshuzaizai.interview.threadPool;

public class MessageContext {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String s){
        THREAD_LOCAL.set(s);
    }

    public static String get(){
        String s = THREAD_LOCAL.get();
        THREAD_LOCAL.remove();
        return s;
    }

}
