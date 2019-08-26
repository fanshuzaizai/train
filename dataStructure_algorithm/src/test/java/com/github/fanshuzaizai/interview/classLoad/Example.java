package com.github.fanshuzaizai.interview.classLoad;

import com.sun.deploy.uitoolkit.DelegatingPluginUIToolkit;
import com.sun.java.accessibility.AccessBridge;
import sun.misc.Launcher;
import sun.reflect.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Example {

    /*
    类加载过程：
        1.编译器将 Cat.java 文件编译成 Cat.class 字节码文件
        2.classLoader根据字节码文件生成一个 Class<Cat> 的类对象
            加载
            1)生成 Class<Cat>类对象
            链接
            2)检查类对象的安全性和正确性
            3)分配内存空间，初始化静态变量，执行静态代码块
            4)将常量池中符号引用改为直接引用
        3.再根据用户代码使用  Class<Cat>类对象 实例化 Cat对象

    ClassLoad种类
        1.BootStrapClassLoader
            jre/lib/rt.jar或-Xbootclasspath制定的位置

        2.ExtClassLoader
            jre/lib/ext/星.jar或-Djava.ext.dirs指定的位置

        3.AppClassLoader
            load classpath 或-Djava.class.path指定的位置（用户代码）

        4.自定义ClassLoader
    ============================================================================

    双亲委派机制
        自定义ClassLoader的parent是AppClassLoader，
        AppClassLoader的parent是ExtClassLoader，
        ExtClassLoader的parent是BootstrapClassLoader

        当使用一个ClassLoader去加载字节码文件时，会从当前classLoader，一直递归向parent查询有无已经装载了该类对象
        如果未装载，则从最上的parent依次检查当前负责范围有没有这个字节码文件。

        好处：
            1.避免重复装载



     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {


        Class<?> aClass = Class.forName("com.github.fanshuzaizai.interview.classLoad.Cat", false, ClassLoader.getSystemClassLoader());
        System.out.println(aClass);
        Constructor<?> constructor = aClass.getConstructor(int.class);
        Cat o = (Cat) constructor.newInstance(10);
        System.out.println(o);
//
//        ClassLoader classLoader = Launcher.getLauncher().getClassLoader();
//        Class<?> aClass1 = classLoader.loadClass("com.github.fanshuzaizai.interview.classLoad.Cat");
//        System.out.println(aClass1);
//        System.out.println(aClass1.newInstance());


        System.out.println(System.getProperty("bootclasspath"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

        System.out.println("====111111111111");

        System.out.println(Launcher.class.getClassLoader());
        System.out.println(DelegatingPluginUIToolkit.class.getClassLoader());
        System.out.println(AccessBridge.class.getClassLoader());

        System.out.println("=====22222222");

        CustomClassLoader customClassLoader = new CustomClassLoader();
        ClassLoader parent = customClassLoader.getParent();
        while (parent != null) {
            System.out.println(parent);
            parent = parent.getParent();
        }

        System.out.println("====333333333");
//        Class<?> caller = Reflection.getCallerClass();
//        System.out.println(caller);


    }
}
