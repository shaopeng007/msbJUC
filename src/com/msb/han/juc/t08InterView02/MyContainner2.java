package com.msb.han.juc.t08InterView02;
/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 *
 * @author mashibing
 */
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainner2<T> {

    final private   LinkedList<T> lists=new LinkedList<>();
    final private  int MAX=10;
    private int count=0;
    private  Lock lock=new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t){
        try {
            lock.lock();
            while(lists.size()==MAX){
                producer.await();
            }

            lists.add(t);
            consumer.signalAll();//唤醒所有消费者线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get(){
        T t=null;
        try {
            lock.lock();
            while(lists.size()==0){
                consumer.await();
            }

            t = lists.removeFirst();
            count--;
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        MyContainner2<String> c=new MyContainner2<>();
        //消费者线程
        for (int i = 0; i < 10; i++) {
          new Thread(()->{
              for (int j = 0; j < 5; j++) {
                  System.out.println(Thread.currentThread().getName()+"----"+c.get());
              }
          },"c"+i).start();
        }

        //生产者
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName()+" "+j);
                }

            },"p"+i).start();
        }
    }

}
