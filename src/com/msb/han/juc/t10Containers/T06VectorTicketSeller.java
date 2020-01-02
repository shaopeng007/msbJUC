package com.msb.han.juc.t10Containers;

import java.util.Vector;

public class T06VectorTicketSeller {

     static Vector<String> vector=new Vector<>();
     static {
         for (int i = 0; i < 1000; i++) {
             vector.add("票编号"+i);
         }
     }
    /*vector 的size()和remove()方法都是线程安全的但是，两个方法连续调用时还是线程不安全的，所以会出错*/
    public static void main(String[] args) {
        for (int i = 0; i <1000 ; i++) {
           new Thread(()->{
               while(vector.size()>0){
                   try {
                       Thread.sleep(10);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println("销售了--"+vector.remove(0));
               }
           }).start();
        }
    }

}
