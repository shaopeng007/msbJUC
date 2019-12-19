package com.msb.han.juc.t06otherLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock用于替代synchronized
 * 由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 * 这里是复习synchronized最原始的语义
 *
 * reentrantLock 可以实现同样的功能
 * 需要注意的是，必须要手动释放锁
 * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 *
 */
public class T02_ReentrantLock02 {
    Lock lock=new ReentrantLock();

    void m1(){
        try {
        lock.lock();
        for (int i = 0; i <10 ; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(i);

        }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void m2(){
        try {
            lock.lock();
            System.out.println("m2...");

        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        T02_ReentrantLock02 r1 = new T02_ReentrantLock02();

        new Thread(r1::m1).start();
        new Thread(r1::m2).start();


    }
}
