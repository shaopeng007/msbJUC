package com.msb.han.juc.t07InterView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 * 分析下面这个程序，能完成这个功能吗？
 * @author mashibing
 */
public class T02_WithVolatile {
    List lists=new ArrayList<Object>();

    //不可以
    /*volatile  int size=0;
    public synchronized void  add(Object o){
        lists.add(o);
        size=lists.size();
    }

    public int size(){ return size;}*/

    public void add(Object o){
        lists.add(o
        );
    }

    public int size(){
        return lists.size();
    }


    public static void main(String[] args) {
        T02_WithVolatile c = new T02_WithVolatile();

        new Thread(() -> {
            for(int i=0; i<10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
            }
        }, "t1").start();

        new Thread(() -> {
            while(true) {
                if(c.size() == 5) {
                    break;
                }
            }
            System.out.println("到达5个");
            System.out.println("t2 结束");
        }, "t2").start();
    }

}
