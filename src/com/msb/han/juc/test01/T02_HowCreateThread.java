package com.msb.han.juc.test01;
/*
* 创建线程的方式
* 继承Thread类
* 实现Runnable接口
* 请你告诉我启动线程的三种方式 1：Thread 2: Runnable 3:Executors.newCachedThrad
* */
public class T02_HowCreateThread {

    static  class T1 extends Thread{
        @Override
        public void run() {
            System.out.println("My Thread");
        }
    }

    static class T2 implements Runnable {


        @Override
        public void run() {
            System.out.println("My Runnable");
        }
    }

    public static void main(String[] args) {
        new T1().start();
        new Thread(new T2()).start();
        new Thread(()->{
            System.out.println("Hello Lambda!");
        }).start();
    }
}
