package com.msb.han.juc.t09_AQS;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {


    public static void main(String[] args) {
        ReentrantLock lock =new ReentrantLock();

        lock.lock();

        lock.unlock();
    }

}
