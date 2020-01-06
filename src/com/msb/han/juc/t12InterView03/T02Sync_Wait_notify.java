package com.msb.han.juc.t12InterView03;

public class T02Sync_Wait_notify {
    public static void main(String[] args) {
        //用两个线程同时运行，交替输出A1B2。。。。Z26
       char [] charA="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
       int [] char1={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
       Object o=new Object();
       new Thread(()->{
           synchronized (o){
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
                try {
                    for (int i = 0; i < char1.length; i++) {
                        System.out.print(char1[i]);
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
