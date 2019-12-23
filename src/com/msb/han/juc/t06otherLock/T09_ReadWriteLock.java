package com.msb.han.juc.t06otherLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T09_ReadWriteLock {
    static Lock lock=new ReentrantLock();
    private static int value;

    static ReadWriteLock readWriteLock= new ReentrantReadWriteLock();

    static Lock readlock=readWriteLock.readLock();
    static Lock writeLock=readWriteLock.writeLock();

    public static void read(Lock lock){

        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over!"+ Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    public  static void write(Lock lock,int v){
        try {
            lock.lock();
            Thread.sleep(1000);
            value=v;
            System.out.println("write over!"+ Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        Runnable readR=()->read(lock);
//        Runnable readR=()->read(readlock);
        Runnable writeR=()->write(lock,new Random().nextInt());
//        Runnable writeR=()->write(writeLock,new Random().nextInt());


        for (int i = 0; i <18 ; i++) {
            new Thread(readR).start();
        }

        for (int i = 0; i <2 ; i++) {
            new Thread(writeR).start();

        }
    }
}
