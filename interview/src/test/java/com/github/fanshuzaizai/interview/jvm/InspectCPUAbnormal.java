package com.github.fanshuzaizai.interview.jvm;

public class InspectCPUAbnormal {

    /*
    cpu占用率过高故障排查
    
    1. top 查看过高进程的pid
    2. ps -mp <pid> -o THREAD,tid,time 查看线程tid
    3. 将 tid 转换为16进制小写，计算器 或者 printf "%x\n" <tid>
    4. jstack <pid> |grep <pid的16进制小写> -A60 打印异常代码栈位置
                                        显示60行
     */

}
