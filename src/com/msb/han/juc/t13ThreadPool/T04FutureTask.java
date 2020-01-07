package com.msb.han.juc.t13ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T04FutureTask {
    public static void main(String[] args) {
        FutureTask<Integer> task=new FutureTask<>(()->{
            TimeUnit.MICROSECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();

        try {
            System.out.println(task.get());//阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
