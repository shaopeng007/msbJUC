package com.msb.han.juc.t06otherLock;

import java.util.concurrent.Semaphore;

public class T10_Semaphore {

    public static void main(String[] args) {
        //允许两个线程同时执行
        Semaphore s=new Semaphore(2);
//        Semaphore s=new Semaphore(1);

        new Thread(()->{
            try {
                s.acquire();
                System.out.println("T1 running...");
                Thread.sleep(2000);
                System.out.println("T1 running....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();
                System.out.println("T2 running...");
                Thread.sleep(2000);
                System.out.println("T2 running....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }
                ).start();
    }


}
