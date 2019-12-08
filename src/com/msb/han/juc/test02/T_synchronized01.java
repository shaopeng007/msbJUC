package com.msb.han.juc.test02;

/**
 * synchronized　关键字
 * 对某个对象加锁
 * @author  hanshaopeng
 */
public class T_synchronized01 {

    private int count=10;
    private Object o=new Object();

    public void m(){
        synchronized (o){//任何线程要执行下面代码，必须先拿到o的锁
            count--;
            System.out.println(Thread.currentThread().getName()+"count="+count);
        }
    }
}
