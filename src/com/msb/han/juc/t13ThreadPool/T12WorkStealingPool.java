package com.msb.han.juc.t13ThreadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T12WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());
        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000)); //deamon
        //由于生产的是精灵线程（守护线程，后台线程），主线程不阻塞的话，看不到结果
        System.in.read();

    }

    static class R implements Runnable{
        int time;

        R (int t){
            this.time=t;
        }
        @Override
        public void run() {
            try {
                TimeUnit.MICROSECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time+""+Thread.currentThread().getName()+" is Daemon"+Thread.currentThread().isDaemon());
        }
    }

}
