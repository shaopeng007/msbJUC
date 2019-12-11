package com.msb.han.juc.t03Volatile;

import java.util.ArrayList;

/**
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * 运行下面的程序，并分析结果
 *
 * @author mashibing
 */
public class VolatileNotSync {

    volatile int count=0;

    public void m(){
        for (int i = 0; i <10000 ; i++) {
            count++;   //count++ 不是原子性操作
        }
    }

    public synchronized void m1(){
        for (int i = 0; i <10000 ; i++) {
            count++;   //count++ 不是原子性操作
        }
    }

    public static void main(String[] args) {
        VolatileNotSync t=new VolatileNotSync();
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i=0; i<10; i++) {
//            threads.add(new Thread(t::m, "thread-"+i));
            threads.add(new Thread(t::m1, "thread-"+i));
        }
        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try {
                o.join();//将线程加入到主线程中，主线程等待所以o线程执行完后再执行完
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);

    }
}
