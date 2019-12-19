package com.msb.han.juc.t06otherLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrantlock还可以指定为公平锁
 */
public class T05_ReentrantLock05 extends Thread {

    private static ReentrantLock lock=new ReentrantLock(/*true*/);

    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }  finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock05 t1=new T05_ReentrantLock05();
        T05_ReentrantLock05 t2=new T05_ReentrantLock05();
       /* Thread th1=new Thread(t1);
        Thread th2=new Thread(t1);

        th1.start();
        th2.start();*/
      t1.start();
      t2.start();
    }
}
