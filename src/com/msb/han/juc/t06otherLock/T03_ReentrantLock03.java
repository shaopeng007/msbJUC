package com.msb.han.juc.t06otherLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock 可以进行“尝试锁定”tryLock， 这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 */
public class T03_ReentrantLock03 {
    Lock lock=new ReentrantLock();

    void m1(){
        try {
            lock.lock();
            for (int i = 0; i <10 ; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    /**
     * 使用trylock进行尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据trylock的返回值判定是否锁定
     * 也可以指定tryLock的时间，由于tryLock(time)抛出异常，所以要注意unlock的处理，必须放到finally中
     * */
    void m2(){
        boolean locked=false;
        try {


            locked=lock.tryLock(5,TimeUnit.SECONDS);

            System.out.println("m2..."+locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) lock.unlock();
        }
    }

    public static void main(String[] args) {
        T03_ReentrantLock03 r1 = new T03_ReentrantLock03();

        new Thread(r1::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r1::m2).start();
    }
}
