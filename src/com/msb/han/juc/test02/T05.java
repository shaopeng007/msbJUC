package com.msb.han.juc.test02;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁.
 * 也就是说synchronized获得的锁是可重入的
 *
 */
public class T05 {

    public synchronized void m1(){
        System.out.println("This is m1");
    }

    public synchronized void m2(){
        this.m1();
        System.out.println("this is m2");
    }


    public static void main(String[] args) {
        T05 t=new T05();

        new Thread(()->t.m2()).start();
    }
}
