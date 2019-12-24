package com.msb.han.juc.t08InterView02;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 *
 * @author mashibing
 */
public class MyContainner1<T> {
    final private LinkedList<T> lists =new LinkedList<>();
    final private int Max=10;
    private int count=0;

    public synchronized void put(T t){
       while (lists.size()==Max){

           try {
               this.wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       }
       lists.add(t);
       ++count;
       this.notifyAll();
    }

    public synchronized  T get(){
        T t=null;
        while (lists.size()==0){

            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        t=lists.removeFirst();

        count --;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainner1<String> c=new MyContainner1<>();
        
        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName()+"...."+c.get());
                }
            },"c"+i).start();
            
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程

        for (int i = 0; i <2 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName()+" "+j);
                }
            },"p"+i).start();
        }
    }

}
