package com.msb.han.juc.t11Containner2;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02CopyOnWriteList {
    public static void main(String[] args) {
        List<String>  lists=
//               new ArrayList<>();
//                new Vector<>();
                new CopyOnWriteArrayList<>();
        Random r=new Random();
        Thread[] ths=new Thread[100];
        for (int i = 0; i < ths.length; i++) {
            Runnable task=new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        lists.add("a"+r.nextInt(1000));
                    }
                }
            };
            ths[i]=new Thread(task);

        }
        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] ths){
        long s1=System.currentTimeMillis();
        Arrays.asList(ths).forEach(t->t.start());
        Arrays.asList(ths).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }
}
