package com.msb.han.juc.t12InterView03;

import java.util.concurrent.atomic.AtomicInteger;

public class T07CAS_AtomicInteger {

    static volatile AtomicInteger A=new AtomicInteger(1);

    public static void main(String[] args) {
        char [] charA="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int [] char1={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};

        new Thread(()->{
            for (char c : charA) {
                while(A.get()!=1){}
                System.out.print(c);
                A.set(2);
            }
        }).start();
        new Thread(()->{
            for (int c : char1) {
                while(A.get()!=2){}
                System.out.print(c);
                A.set(1);
            }
        }).start();
    }
}
