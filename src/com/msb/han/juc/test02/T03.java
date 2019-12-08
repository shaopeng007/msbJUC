package com.msb.han.juc.test02;

/**
 * 分析一下这个程序的输出
 */
public class T03 implements Runnable{

    private int count=100;
    @Override
    public /*synchronized*/ void run() {
        //不加synchronized ，会出现脏读的问题，多个线程会对同一个count--，打印出多个相同的count值

        count--;
        System.out.println(Thread.currentThread().getName()+"count="+count);

    }


    public static void main(String[] args) {
        T03 t=new T03();

        for (int i = 0; i <100 ; i++) {
            new Thread(t,"THREAD"+i).start();
        }
    }
}
