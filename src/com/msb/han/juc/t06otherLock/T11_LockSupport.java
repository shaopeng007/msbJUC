package com.msb.han.juc.t06otherLock;

import java.util.concurrent.locks.LockSupport;

public class T11_LockSupport {

    public static void main(String[] args) {

       Thread t= new Thread(()->{
           for (int i = 0; i <10 ; i++) {
                if (i==5){
                    LockSupport.park();
                }
               System.out.println(i);
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
           System.out.println("t...end");
       });

       t.start();

       LockSupport.unpark(t);
       /* try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after 8 senconds!");
        LockSupport.unpark(t);*/

    }

}
