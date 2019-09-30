package com.github.fanshuzaizai.interview.jvm.error;

import sun.misc.VM;

import java.nio.ByteBuffer;

public class DirectBufferMemoryDemo {


    /*
    直接内存溢出
    在使用nio中，可以直接分配本地内存（direct memory）

    相比于jvm中堆中的内存，不需要复制，所以速度很快
    但可能出现，本地内存已经快满了，但堆中内存还很充足，也就不会触发gc。就会产生 错误
     */
    public static void main(String[] args) {
        //堆外内存大小，默认和 Xmx一样大
        System.out.println(VM.maxDirectMemory() / 1024 / 1024);

        //-Xmx5m
        ByteBuffer.allocateDirect(1024 * 1024 * 20); //java.lang.OutOfMemoryError: Direct buffer memory
    }

}
