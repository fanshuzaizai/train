package com.github.fanshuzaizai.interview.jvm.gc;

/**
 * 1.GC Roots:
 * 可达性分析，（有向图）
 * 一个根引用集合,垃圾回收时，会进行引用遍历，判断其他对象是否引用可达，不可达即为可回收的垃圾
 * 范围：虚拟机栈中的引用、类静态属性引用、常量引用（String、Class）、native方法引用的对象
 */
public class GCRoots {

    public static void main(String[] args) {


    }

}
