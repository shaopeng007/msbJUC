package com.msb.han.juc.t06otherLock;

import java.util.concurrent.Exchanger;

public class T12_Exchanger {

    static Exchanger<String> exChanger =new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            String s="T1";
            try {
               s=exChanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"----"+s);
        },"t1").start();

        new Thread(()->{
            String s="T2";
            try {
                s=exChanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-------"+s);
        },"t2").start();

    }

}
