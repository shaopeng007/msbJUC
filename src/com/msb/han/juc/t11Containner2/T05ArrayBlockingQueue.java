package com.msb.han.juc.t11Containner2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T05ArrayBlockingQueue {

    static BlockingQueue<String> queue=new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <10 ; i++) {
           queue.put("a"+i);
        }
        System.out.println(queue);
//        queue.put("aa");  //会阻塞
        queue.add("aa");
        queue.offer("aa");
        queue.offer("aa", 1,TimeUnit.SECONDS);
        System.out.println(queue);
    }
}
