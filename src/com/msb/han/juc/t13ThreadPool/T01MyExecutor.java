package com.msb.han.juc.t13ThreadPool;

import java.util.concurrent.Executor;

/**
 * 定义和任务的执行分开了
 */
public class T01MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        System.out.println("任务开始执行了");

        command.run();
    }

    public static void main(String[] args) {

        new T01MyExecutor().execute(()->{
            System.out.println("正在执行任务");
        });
    }
}
