package com.msb.han.juc.t08InterView02;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainner2<T> {

    final private   LinkedList<T> lists=new LinkedList<>();
    final private  int MAX=10;
    private int count=0;
    private  Lock lock=new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t){
        try {
            lock.lock();
            while(lists.size()==MAX){
                producer.await();
            }

            lists.add(t);
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
