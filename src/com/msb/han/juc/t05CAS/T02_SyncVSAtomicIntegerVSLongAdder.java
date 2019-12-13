package com.msb.han.juc.t05CAS;

import com.msb.han.juc.t01ThreadConcept.ThreadState;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class T02_SyncVSAtomicIntegerVSLongAdder {

    static Long Lcount=0L;

    static AtomicInteger Acount =new AtomicInteger(0);

    static LongAdder LAcount=new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread [] threads=new Thread[1000];

        for (int i = 0; i <threads.length ; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j <100000 ; j++) Acount.incrementAndGet();
            });

        }

        long start=System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }

        for(Thread t:threads) {
            t.join();
        }
       long end=System.currentTimeMillis();

        System.out.println("AtomicInteger:"+Acount.get()+"time"+(end-start));

        Object lock=new Object();
        for (int i = 0; i <threads.length ; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j <100000 ; j++) {
                    synchronized (lock){
                        Lcount++;
                    }
                }
            });


        }

        start=System.currentTimeMillis();

        for (Thread t:threads) {
            t.start();
        }

        for (Thread t:threads)t.join();

        end=System.currentTimeMillis();

        System.out.println("Long"+Lcount+"time"+(end-start));

        for (int i = 0; i <threads.length ; i++) {

            threads[i]=new Thread(()->{
                for (int j = 0; j <100000 ; j++) {
                    LAcount.increment();
                }

            });

        }

        start=System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }

        for(Thread t:threads) {
            t.join();
        }
        end=System.currentTimeMillis();

        System.out.println("LongAdder:"+LAcount.longValue()+"time"+(end-start));
    }

}