package com.github.fanshuzaizai.interview.jvm.error;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MetaSpaceDemo {
    //com.github.fanshuzaizai.interview.jvm.error.MetaSpaceDemo.ABC
    static class ABC {
    }

    /*
    元空间内存溢出
    jdk8使用元空间代替了永久代，元空间使用本地内存而不是jvm内存
     */
    public static void main(String[] args) {
        //-XX:MetaspaceSize=5m -XX:MaxMetaspaceSize=5m
        int i = 0;

        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(ArrayList.class);
                enhancer.setUseCache(false);
                enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invoke(o, objects));
                enhancer.create();
            }
        } catch (Throwable t) {
            System.out.println(i);
            t.printStackTrace(); //java.lang.OutOfMemoryError: Metaspace
        }

//        enhancer.s
    }

}
