package com.msb.han.juc.t02Sync;

import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中，如果出现异常，默认情况锁会被释放
 * 所以，在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况。
 * 比如，在一个web app处理过程中，多个servlet线程共同访问同一个资源，这时如果异常处理不合适，
 * 在第一个线程中抛出异常，其他线程就会进入同步代码区，有可能会访问到异常产生时的数据。
 * 因此要非常小心的处理同步业务逻辑中的异常
 * @author mashibing
 */
public class T07 {
    private int count=0;
    public synchronized void m(){
        for (int i = 0; i <10; i++) {
            count++;
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"-----"+count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           if (i==5){
               int c=i/0;//此处抛出异常，锁将被释放，要想不被释放，可在这里进行catch让循环继续
               System.out.println(c);
           }
        }


    }

    public static void main(String[] args) {
        T07 t = new T07();
        Runnable run=new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };

        new Thread(run,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(run,"t2").start();

    }


}
