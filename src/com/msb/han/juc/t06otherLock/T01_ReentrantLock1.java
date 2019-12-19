package com.msb.han.juc.t06otherLock;

import java.util.concurrent.TimeUnit;

/**
 * reentrantlock 用于代替synchronized
 * 本例中由于m1 锁定this,只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized的原始语义
 */
public class T01_ReentrantLock1 {
    synchronized void m1(){
        for (int i = 0; i <10 ; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
          if (i==2){
              m2();
              System.out.println("这里是可重入的特性");
          }
        }
    }

    synchronized void m2() {

        System.out.println("m2.....");
    }


    public static void main(String[] args) {
        T01_ReentrantLock1 r1=new T01_ReentrantLock1();
        /*new Thread(){
            @Override
            public void run() {
                r1.m1();
            }
        }.start();*/

        new Thread(()->{r1.m1();}).start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
              r1.m2();
            }
        }).start();*/
        new Thread(r1::m2).start();


    }
}
