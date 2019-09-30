package com.github.fanshuzaizai.interview.concurrent.lock;

/**
 *
 * synchronized：
 *  jvm实现，需手动释放，利用monitor对象
 *
 * lock：
 *  需要手动释放锁
 *  可以被中断（tryLock，interrupt）
 *  可选公平锁和非公平锁
 *  支持多个condition
 *  利用condition精确唤醒
 *
 */