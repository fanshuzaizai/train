package com.github.fanshuzaizai.interview.classLoad;

import com.sun.deploy.uitoolkit.DelegatingPluginUIToolkit;
import sun.misc.Launcher;

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
                3)为静态变量分配内存空间，初始默认值
                    ①如果是 static 变量，则赋予其该类型的默认值（int=0，boolean=false），而不是字面赋予的值
                    ②如果是 static final 变量，则赋予其字面量
                4)将常量池中符号引用改为直接引用
        3.初始化
            如果没有加载和链接，那么先执行上面步骤
            如果父类没有初始化，则先初始化父类
            1)步骤
                执行static静态代码块
                为 static 变量赋予字面值
                为普通变量赋予对应类型的默认值
            2)时机
                new 对象
                调用静态方法，静态属性
                反射（ Class.forName("") ）
                初始化某个子类，那么父类也会被初始化
                被标记为启动类的类
        4.再根据用户代码使用  Class<Cat>类对象 实例化 Cat对象

    ClassLoad种类
        1.BootStrapClassLoader 由c++实现
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
//        Cat o = (Cat) constructor.newInstance(10);
//        System.out.println(o);
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

        System.out.println("=====22222222");

        CustomClassLoader customClassLoader = new CustomClassLoader("123");
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
