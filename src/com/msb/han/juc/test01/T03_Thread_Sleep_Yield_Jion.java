package com.msb.han.juc.test01;

public class T03_Thread_Sleep_Yield_Jion {
    public static void main(String[] args) {
//        testSleep();
//        testyield();
        testJoin();
    }

    static void testSleep() {
        new Thread(() -> {

            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }

    static void testyield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }

            }
        }
        ).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("--------B" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }

            }
        }
        ).start();

    }

    static void testJoin(){
        Thread t1 = new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                System.out.println("----T1--"+i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2=new Thread(()->{
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i <10; i++) {
                System.out.println("T2-----"+i);
            }
        });
        t1.start();
        t2.start();
    }

}
