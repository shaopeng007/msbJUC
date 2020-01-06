package com.msb.han.juc.t12InterView03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class T05Lock_Condition {
    public static void main(String[] args) {
        //用两个线程同时运行，交替输出A1B2。。。。Z26

        char [] charA="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int [] char1={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
        ReentrantLock lock=new ReentrantLock();

        Condition condition = lock.newCondition();

        new Thread(()->{
            try {
                lock.lock();

                for (char c : charA) {
                    System.out.println(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

        new Thread(()->{
            try {
                lock.lock();
                for (int c : char1) {
                    System.out.println(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();
    }
}
