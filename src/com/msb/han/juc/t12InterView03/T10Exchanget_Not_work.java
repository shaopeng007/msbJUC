package com.msb.han.juc.t12InterView03;

import java.util.concurrent.Exchanger;

public class T10Exchanget_Not_work {

    public static void main(String[] args) {
        char [] charA="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int [] char1={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};

        Exchanger exchanger=new Exchanger();

        new Thread(()->{
            for (char c : charA) {
                try {
                    exchanger.exchange("s");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(c);
            }

        }).start();
        new Thread(()->{
            for (int c : char1) {
                try {
                    exchanger.exchange("ss");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(c);
            }

        }).start();

    }


}
