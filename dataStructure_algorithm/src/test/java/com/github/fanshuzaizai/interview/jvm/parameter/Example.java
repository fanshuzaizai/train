package com.github.fanshuzaizai.interview.jvm.parameter;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * <p>
 * <p>
 * jvm参数类型
 * 1.标配参数：-version、-help
 *
 * <p>
 * 2.X参数
 * <p>
 * 3.XX参数
 * boolean类型：
 * 开启：-XX:+参数
 * 关闭：-XX:-参数(-XX:-PrintGCDetails)
 * kv类型：
 * -XX:key=value(-XX:MetaspaceSize=21807104)。注意：-Xms等于-XX:initialHeapSize、-Xmx等于-XX:MaxHeapSize，只是相当于别名
 *
 * <p>
 * <p>
 * 查看运行的java进程：jps -l
 * <p>
 * 查看正在运行的java进程的jvm参数： jinfo -flag PrintGCDetails 进程号
 * 查看所有：jinfo -flags
 * 修改：   jinfo -flag [-|+]PrintGCDetails 进程号
 * jinfo -flag k=v 进程号
 * <p>
 * 查看jvm参数：
 * 初始参数：        java -XX:+PrintFlagsInitial
 * 实际生效的参数：  java -XX:+PrintFlagsFinal（ :=表示人为或根据配置自动修改过的值）
 * java -XX:+PrintCommandLineFlags（主要看 -XX:+UseParallelGC 参数）
 */
public class Example {

    /*
    jvm参数类型
        1.标配参数：-version、-help
        2.X参数
        3.XX参数
            boolean类型：
                开启：-XX:+参数
                关闭：-XX:-参数(-XX:-PrintGCDetails)
            kv类型：
                -XX:key=value(-XX:MetaspaceSize=21807104)。

    查看正在运行的java进程的jvm参数：
        1.查看运行的java进程：jps -l
        2.根据进程号查看参数： jinfo -flag PrintGCDetails 进程号
            查看所有：jinfo -flags
            修改：   jinfo -flag [-|+]PrintGCDetails 进程号
                    jinfo -flag k=v 进程号

    查看jvm参数：
        初始参数：        java -XX:+PrintFlagsInitial
        实际生效的参数：  java -XX:+PrintFlagsFinal（ :=表示人为或根据配置自动修改过的值）
        java -XX:+PrintCommandLineFlags（主要看 -XX:+UseParallelGC 参数）

    常用参数：
        -Xmx
            等于-XX:MaxHeapSize(默认物理内存1/4)
        -Xms
            等于-XX:initialHeapSize(默认物理内存1/64)
        -Xss
            等于-XX:ThreadStackSize(0表示使用系统默认值，64位linux默认1024k，windows根据虚拟内存)
        -XX:MetaSpaceSize
            元空间大小，默认不到21m
        -XX:+PrintCommandLineFlags
            打印几个主要jvm参数
        -XX:+UseParallelGC
            垃圾回收器类型
        -XX:+PrintGCDetails
            打印gc细节
        -XX:SurvivorRatio
            设置新生代中Eden和S0/S1（from、to）的比例，如果设置为4，表示 Eden:S0:S1 大小为 4:1:1。默认值8
            eden space 29184K...,from space 7168K..., to space 7168K
        -XX:NewRatio
             设置老年代的占例，如果设置为4，表示新生代占1，老年代占4，新生代占整个堆的1/5。默认值2
             PSYoungGen      total 22016K
             ParOldGen       total 104960K
        -XX:MaxTenuringThreshold
            设置垃圾的年龄，如果设置为0，则新生代不经过Survivor区（from、to），直接进入老年代，如果设置一个较大的值，可以提高在新生代的存活时间。默认值15
            注意，jdk8强制只能设置0-15之间

     */
    public static void main(String[] args) throws InterruptedException {

        MathContext mathContext = new MathContext(1);
        int years = 0;

        BigDecimal low = new BigDecimal("1000000");

        BigDecimal high = new BigDecimal("10000000");

        while (low.compareTo(high) < 0) {
            low = low.multiply(new BigDecimal("1.03")).add(new BigDecimal("400000")).setScale(0, RoundingMode.HALF_DOWN);
            high = high.multiply(new BigDecimal("1.03")).add(new BigDecimal("50000")).setScale(0, RoundingMode.HALF_DOWN);
            years++;
            if (years % 10 == 0) {
                System.out.println(low.toPlainString());
                System.out.println(high.toPlainString());
                System.out.println(years);
                System.out.println("========");
            }
        }

        System.out.println("***over***");
        System.out.println(years);
    }
}
