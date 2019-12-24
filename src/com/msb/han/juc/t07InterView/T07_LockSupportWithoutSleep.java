package com.msb.han.juc.t07InterView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T07_LockSupportWithoutSleep {

    List lists=new ArrayList();

    public void add(Object o){lists.add(o);}

    public int size(){return lists.size();}

    public static void main(String[] args) {

        T05_CountDownLatch c=new T05_CountDownLatch();


      Thread t2=  new Thread(()->{
          System.out.println("t2 启动");
            LockSupport.park();
          System.out.println("t2 结束");
        },"t2");

        Thread t1=   new Thread(()->{
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {

                c.add(new Object());
                System.out.println("add "+i);
                if (c.size()==5){
                 LockSupport.unpark(t2);
                  /*  try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }

            }
            System.out.println("t1 结束");
        },"t1");

        t2.start();
       /* try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        t1.start();
    }

}
