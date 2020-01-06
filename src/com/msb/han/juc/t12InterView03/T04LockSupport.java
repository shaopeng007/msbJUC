package com.msb.han.juc.t12InterView03;

import java.util.concurrent.locks.LockSupport;

public class T04LockSupport {
   static Thread t1=null ,t2=null;
    public static void main(String[] args) {
        //用两个线程同时运行，交替输出A1B2。。。。Z26
        char [] charA="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int [] char1={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};

       t1 = new Thread(()->{
            for (char c : charA) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }

        });

         t2= new Thread(()->{
            for (int c : char1) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }

        });
         t1.start();
         t2.start();
    }

}
