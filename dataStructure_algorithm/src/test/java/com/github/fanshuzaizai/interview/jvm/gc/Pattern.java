package com.github.fanshuzaizai.interview.jvm.gc;

public class Pattern {
    /*
    jvm 堆内存区域：
        新生代（1/3），其中又包括 Eden（8/10）、survivor0（1/10）、survivor1（1/10）

        老年代turned（2/3）

    GC种类
        minor GC: 当Eden空间不足会触发，专门清理年轻代
            新对象创建后，首先放在Eden区中，当执行minor GC时，会把存活的对象放入s0区中，并把这些对象的年龄+1，然后清空Eden区
            当下一次执行minor GC时，会把Eden和s0中存活的对象移动到s1区中，并把这些对象年龄+1，再清空Eden和s1
            s0和s1会来回复制，保证有一个区域是空的

        full GC：速度比minor GC满，但触发频率低
            1.当老年代（永久代，jdk8以前）空间不足会触发，清理年轻代+老年代
            2.System.gc();//提醒虚拟机，不是绝对的
            3.CMS GC 时抛出promotion failed,concurrent mode failure
            4.年轻代移动到老年代的平均大小，大于当前老年代的剩余空间
            当survivor区中的对象年龄超过15时，就会放入老年代
            或者survivor放不下的对象，会直接移入老年代
            大对象（-XX:+PreTenuerSizeThreshold）会直接放入老年代


    ==================================================================

    四种垃圾回收算法

    1.引用计数
        增加1个引用就+1，减少1个引用就-1，到0就回收。
        缺点：无法解决循环引用。
        目前不使用这种方法。


    2.复制
        遍历当前区域后，将需要保留的对象，全部复制到另一个区域中，再将当前区域直接清除，避免内存碎片的产生。
        新生代中使用，因为每次新生代清楚垃圾后，只会存活很少的对象，这样复制的开销也比较小，
        而且要防止100%对象全部存活的极端情况，就需要预备2个一样大的区域来执行copy操作，
        所以不适合老年代这种存活率很高、体积很大的区域。


    3.标记-清除
        将需要清理的对象打上标记，再执行清除，会产生内存碎片
        老年代使用


    4.标记-(清除-)整理
        将需要清理的对象打上标记，再执行清除，并定期重新排序，消除内存碎片。
        老年代使用

    ===============================================================================

    四种具体垃圾收集器
        java8主要3种Serial、Parallel、CMS

        1.Serial 串行垃圾回收器
            只使用一个线程进行gc，且会暂停用户线程（Stop-the-world），不适合生产环境


        2.Parallel 并行垃圾回收器
            使用多个线程机进行gc，也会暂停用户线程，适合计算等弱交互场景

        3.CMS 并发标记清除垃圾回收器
            清理线程大部分过程能和用户线程同时执行，互联网公司多使用这种方式
            缺点：
            流程:
                1.initial mark：标记GC Roots直接关联的对象，需要暂停用户线程
                2.concurrent mark：从第一步标记的对象出发，遍历所有可达对象，和用户线程并发执行
                3.remark：修正concurrent mark过程中标记对象，需要暂停用户线程
                4.concurrent sweep：清理，和用户线程并发执行
            如果CMS没有在老年代堆内存用尽前完成回收垃圾，将触发担保机制，会由Serial Old 来进行一次GC，造成较大时间停顿
            标记清除无法整理内存碎片，后期将不得不触发担保机制
            也可通过 -XX:CMSFullGCsBeForeCompaction （默认0）来制定多少次CMS后触发一个Full GC 来整理碎片

        4.G1
            java8可以使用，java9的默认垃圾回收器，没有内存碎片
            不再将堆内存整体划分成eden，survivor，old区，而是分割成很多小块，每块都可能是任区域，且会改变。
            清理新生代块（eden，survivor）时依然会暂停用户线程
            清理老年代块会直接复制到另一个块中，避免了内存碎片的问题
            还有一种 Humongous区(超大对象区)，用于存储很大的对象，如果超过块的大小，就会用数个连续的块来存储，但这时可能会触发Full GC

            和CMS一样分为4个步骤

            相对于CMS的优势
                1.不会产生内存岁哦
                2.可以设置暂停时间

     ===============================================================================

     查看当前垃圾收集器 -XX:+PrintCommandLineFlags

     不同jdk差别很大，hotspot：
     所有可用GC：UseSerialGC、UseParallelGC、UseConcMarkSweepGC、UseG1GC

     不同区域可用垃圾收集齐:
         新生代:
            SerialGC 复制
            ParNew 复制
            Parallel[Scavenge] 复制
            G1 整体采用标记-整理，局部采用复制
         老年代:
            SerialOldGC 标记-整理
            ParallelOldGC 标记-整理
            CMS 标记-清除
            G1

     可用组合：
        -XX:+UseSerialGC
            新生代、老年代都使用 串行垃圾回收器
            新生代使用复制算法，老年代使用标记-整理算法

        -XX:+UseParNewGC
            新生代并行垃圾回收器，老年代默认使用串行（Serial Old）。会提示组合已被废弃
            -XX:ParallelGCThreads 线程数量，默认和cpu核数相同

        -XX:+UseParallelGC 会自动开启 -XX:+UseParallelOldGC
            Parallel Scavenge 新生代
            UseParallelOldGC 老年代
            和 ParNew 有区别：
                1.控制吞吐量：执行用户代码时间/总时间
                2.自适应调节策略：动态调整停顿时间(或-XX:MaxGCPauseMillis)和吞吐量

        -XX:+UseConcMarkSweepGC
            老年代 并发标记清除 垃圾回收器，会自动开启 UseParNewGC

        -XX:+UseG1GC
            -XX:G1HeapRegionSize=n 块大小,值必须2的幂，1m-32m，将划出大约2048个区域，也就是最大内存64G
            -XX:MaxGCPauseMillis=n 最大停顿目标，软目标，JVM尽可能(不保证)停顿时间小于这个值
            -XX:InitiatingHeapOccupancyPercent=n 堆占用多少比例触发GC，默认45
            -XX:ConcGCThreads=n 执行GC的线程数
            -XX:G1ReservePercent=n 预留空闲空间的百分比，降低溢出风险，默认10



        ParNew+CMS、Parallel+Parallel Old、G1


     */
}
