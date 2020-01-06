package com.msb.han.juc.t12InterView03;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class T08BlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue1 = new ArrayBlockingQueue(1);
        BlockingQueue<String> queue2 = new ArrayBlockingQueue(2);
        char[] charA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int[] char1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        queue1.put("aaa");
        new Thread(() -> {
            for (char c : charA) {
                try {
                    queue1.take();
                    System.out.print(c);
                    queue2.put("aa");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int c : char1) {
                try {
                    queue2.take();
                    System.out.print(c);
                    queue1.put("aa");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
