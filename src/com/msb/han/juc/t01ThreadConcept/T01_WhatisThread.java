package com.msb.han.juc.t01ThreadConcept;

import java.util.concurrent.TimeUnit;

/*
 * 什么是线程
 * 一个程序里不同的执行路径
 * */
public class T01_WhatisThread {
    private static class T1 extends Thread {

        @Override
        public void run(){
            for (int i = 0; i <100 ; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
//        new T1().run();
        new T1().start();
        for (int i = 0; i <100 ; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }

}
