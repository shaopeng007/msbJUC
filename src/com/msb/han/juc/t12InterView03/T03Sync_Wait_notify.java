package com.msb.han.juc.t12InterView03;

import java.util.concurrent.CountDownLatch;

public class T03Sync_Wait_notify {
    private static volatile  boolean t2Started=false;

    public static void main(String[] args) {
        CountDownLatch latch=new CountDownLatch(1);
        //用两个线程同时运行，交替输出A1B2。。。。Z26
       char [] charA="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
       int [] char1={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
       Object o=new Object();
       new Thread(()->{
           try {
               latch.await();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           synchronized (o){
                 /*  while(!t2Started){
                       try {
                           o.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }*/
                   try {
                       for (int i = 0; i < charA.length; i++) {
                           System.out.print(charA[i]);
                           o.notify();
                           o.wait(); //线程等待，让出锁
                       }
                       o.notify(); //必须否则程序无法终止
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }


       }).start();
        new Thread(()->{
            synchronized (o){
//                t2Started=true;
                try {
                    for (int i = 0; i < char1.length; i++) {
                        System.out.print(char1[i]);
                        latch.countDown();
                        o.notify();
                        o.wait();
                    }
                    o.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
