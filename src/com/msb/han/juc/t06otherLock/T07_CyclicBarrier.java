package com.msb.han.juc.t06otherLock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T07_CyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier barrier=new CyclicBarrier(20,()-> System.out.println("满人"));

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName());
                    barrier.await();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }



}
