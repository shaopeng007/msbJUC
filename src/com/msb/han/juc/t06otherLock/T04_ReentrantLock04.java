package com.msb.han.juc.t06otherLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock 还可以调用LockInterruptibly方法，可以对线程Interrupt 方法做出响应
 * */
public class T04_ReentrantLock04 {
    public static void main(String[] args) {
        Lock lock=new ReentrantLock();

        Thread T1=new Thread(()->{

            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1  end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                lock.unlock();
            }

        });

        T1.start();

        Thread T2=new Thread(()->{
            try {
                lock.lockInterruptibly();
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted");
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        });

        T2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        T2.interrupt();//打断线程2的等待
    }
}
