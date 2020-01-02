package com.msb.han.juc.t10Containers;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class T08QueueTicketSeller {
    static Queue<String> q=new ConcurrentLinkedDeque<>();
    static {
        for (int i = 0; i < 1000; i++) {
            q.add("票编号"+i);
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<10; i++) {
            new Thread(()->{
                while(true) {
                    String s = q.poll();
                    if(s == null) break;
                    else System.out.println("销售了--" + s);
                }
            }).start();
        }
    }
}
