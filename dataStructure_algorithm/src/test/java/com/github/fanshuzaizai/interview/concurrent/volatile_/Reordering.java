package com.github.fanshuzaizai.interview.concurrent.volatile_;

/**
 * 指令重排序
 *
 * @author Jzy.
 * @date 2019/8/15 11:20
 */
public class Reordering {

    //防止指令重排序
    public volatile static Reordering INSTANCE;


    public Reordering getInstance() {
        //双重检查
        if (INSTANCE == null) {
            synchronized (this) {
                if (INSTANCE == null) {
                    /*
                        底层操作分3个步骤
                        1.开辟内存地址空间
                        2.在地址中初始化对象
                        3.将引用指向地址

                        2，3对1有依赖关系，所以1会在前面执行
                        但2，3之间没有依赖关系，可能发生指令重排序，也就是可能先执行3，再执行2
                        也就是可能会导致 引用 指向 内存地址，但这个内存地址中还未生成对象，其他线程就拿去使用，导致发生错误
                     */
                    INSTANCE = new Reordering();
                }
            }
        }
        return INSTANCE;
    }

}
