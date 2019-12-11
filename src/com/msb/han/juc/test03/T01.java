package com.msb.han.juc.test03;

import com.msb.han.juc.test02.T03;

import java.util.concurrent.TimeUnit;

/*
 * 保证线程可见性
 * */
public class T01 {
//    boolean running = true;

    volatile boolean running=true;
    public void m() {
        System.out.println("m--start");
        while (running){

        }
        System.out.println("m---end");
    }

    public static void main(String[] args) {
        T01 t = new T01();
        new Thread(t::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running=false;

    }
}
