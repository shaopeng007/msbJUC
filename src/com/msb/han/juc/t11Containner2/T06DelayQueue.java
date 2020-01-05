package com.msb.han.juc.t11Containner2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 根据等待的时间进行排序
 */
public class T06DelayQueue {
    static BlockingQueue<MyTask> tasks=new DelayQueue<>();

    static class MyTask implements Delayed {
        String name;
        long runningTime;

        MyTask(String name, long rt) {
            this.name = name;
            this.runningTime = rt;
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }

        @Override
        public long getDelay(TimeUnit unit) {

            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }


        @Override
        public String toString() {
            return name + " " + runningTime;
        }
    }

}
