package com.github.fanshuzaizai.interview.jvm.memory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Example {

    /*
    jvm运行时数据区：
        栈 stack：每个方法被执行的时候会创建一个栈帧，每个方法被调用直至执行完成的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。
            栈帧包含：
                局部变量表，每个空间32位，double，long会占用2个空间，空间会在编译器就被分配
                    1）编译器可知的基本数据类型
                    2）对象引用
                操作栈
                方法出口
                动态链接
            每个线程独有，相互隔离
            每个线程还应该有Native stack，HotSpot虚拟机将2者合并

        堆 heap：存储 对象、数组
            所有线程共享的区域

        程序计数器：每个线程独有
            一块较小的内存空间，不会造成OOM，根据这个执行下一个字节码命令


        元空间 metaspace：存放在本地内存中，而不是jvm内存中




        栈stack：每个线程都有独立的stack，相互隔离

        java栈（VM stack）
            栈帧
            本地基础变量（8种）以对象引用

    堆heap：
        对象实例

     */
    public static void main(String[] args) {
        //编译期 常量池已经存在 "1" ,"11"

        //s3指向堆中的一个对象，假设地址是0x0056
        String s3 = new String("1") + new String("1");

        //试图将 "11" 放入常量池，但常量池已经有了一样的值，
        //JDK6则直接返回常量池的引用
        //而JDK7会将常量池中的数据（C中的符号链接symbol，可以理解为字面量）改成指向这个堆中的对象的引用
        String s4 = "11";//s4指向常量池，假设常量池地址是0x0094

        System.out.println(s3 == s4);//true ，相当于 s3指向堆中的对象0x0056，而s4指向常量池中的0x0094，而这个0x0094 又指向 0x0056

        //s5指向堆中的一个对象，假设地址是0x0023
        String s5 = new String("11");
        s5.intern();//将常量池中 "11"的引用，从指向s3的0x0056，改为 指向当前的 0x0023

        System.out.println(s3 == s4);

        System.out.println(s4 == s5);


    }

    static final Unsafe unsafe = getUnsafe();
    static final boolean is64bit = true;

    public static void printAddresses(String label, Object... objects) {
        System.out.print(label + ": 0x");
        long last = 0;
        int offset = unsafe.arrayBaseOffset(objects.getClass());
        int scale = unsafe.arrayIndexScale(objects.getClass());
        switch (scale) {
            case 4:
                long factor = is64bit ? 8 : 1;
                final long i1 = (unsafe.getInt(objects, offset) & 0xFFFFFFFFL) * factor;
                System.out.print(Long.toHexString(i1));
                last = i1;
                for (int i = 1; i < objects.length; i++) {
                    final long i2 = (unsafe.getInt(objects, offset + i * 4) & 0xFFFFFFFFL) * factor;
                    if (i2 > last)
                        System.out.print(", +" + Long.toHexString(i2 - last));
                    else
                        System.out.print(", -" + Long.toHexString(last - i2));
                    last = i2;
                }
                break;
            case 8:
                throw new AssertionError("Not supported");
        }
        System.out.println();
    }

    private static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
