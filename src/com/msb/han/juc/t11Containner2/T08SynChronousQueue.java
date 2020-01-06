package com.msb.han.juc.t11Containner2;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class T08SynChronousQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue=new SynchronousQueue<>();

        new Thread(()->{

            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        queue.put("aaa");
//        queue.add("aaa");
        System.out.println(queue.size());
    }
}
