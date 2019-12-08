package com.msb.han.juc.test02;

/**
 * synchronized(this)
 * synchronized 方法  相同的锁定的代码 来说效果是一样的
 */
public class T02 {

    private int count=10;

    private  static int count1=10;

    public void m(){
        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName()+"count="+count);
        }
    }

    public synchronized void m1(){  //这里等同于synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName()+"count="+count);
    }

    public static synchronized  void m2(){//这里等同于synchronized(T.class)
        count1--;
        System.out.println(Thread.currentThread().getName()+"count1="+count1);
    }
}
