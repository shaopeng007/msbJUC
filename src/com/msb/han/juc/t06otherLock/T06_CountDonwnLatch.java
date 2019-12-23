package com.msb.han.juc.t06otherLock;

import java.util.concurrent.CountDownLatch;

public class T06_CountDonwnLatch {


    public static void main(String[] args) {
//     usingCountDownLatch();
        usingJoin();
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
                System.out.println(Thread.currentThread().getName() + "--" + result);
                latch.countDown();
            });

        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();

        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch");
    }


    private static void usingJoin() {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;

                }
                System.out.println(Thread.currentThread().getName() + "---" + result);
            });

        }


        for (int i = 0; i < threads.length; i++) {
            threads[i].start();

        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("end join");
    }
}

