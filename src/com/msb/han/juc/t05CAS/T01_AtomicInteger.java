package com.msb.han.juc.t05CAS;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class T01_AtomicInteger {


    AtomicInteger count=new AtomicInteger(0);

    void m(){
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T01_AtomicInteger t=new T01_AtomicInteger();

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m,"thread:"+i));
        }

        threads.forEach((o)->o.start());

        for (Thread o : threads) {
            try {
                o.join();//mian线程中调用o.join,将o线程加入到main线程运行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(t.count);
    }
}
