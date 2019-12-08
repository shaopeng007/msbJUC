package com.msb.han.juc.test02;

/**
 * 分析一下这个程序的输出
 */
public class T03 implements Runnable{

    private int count=100;
    @Override
    public /*synchronized*/ void run() {
        //synchronized 既保证原子性，又保证可见性
        //不加synchronized,最后打印的数据会出现问题，两个线程都对count进行--后一起输出。
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
