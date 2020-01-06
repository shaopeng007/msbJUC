package com.msb.han.juc.t12InterView03;

import com.msb.han.juc.t11Containner2.TransferQueue;

import java.util.concurrent.LinkedTransferQueue;

public class T11TransferQueue {

    public static void main(String[] args) {
        char [] charA="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int [] char1={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};

        LinkedTransferQueue<Object> queue =new LinkedTransferQueue<>();

        new Thread(()->{
            for (int c : char1) {
                try {
                    System.out.print(queue.take());
                    queue.transfer(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            for (char c : charA) {
                try {
                    queue.transfer(c);
                    System.out.print(queue.take());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
