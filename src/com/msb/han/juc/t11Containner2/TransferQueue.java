package com.msb.han.juc.t11Containner2;


import java.util.concurrent.LinkedTransferQueue;

public class TransferQueue {

    public static void main(String[] args) throws InterruptedException {
//    BlockingQueue<String> str=new LinkedTransferQueue<>();
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        new Thread(()->{

            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        queue.transfer("aaa");
    }

}
