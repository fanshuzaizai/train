package com.github.fanshuzaizai.interview;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

class NewTest2 {
    public static void main(String[] args) {



        Callable<String> callable = new Callable<String>() {

            @Override
            public String call() throws RuntimeException {
                return "123";
            }
        };


        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        Callable<Object> callable1 = Executors.callable(runnable);


        new Thread();




        Class<?> aClass = Proxy.getProxyClass(NewTest2.class.getClassLoader(), NewTest2.class);




    }
}
