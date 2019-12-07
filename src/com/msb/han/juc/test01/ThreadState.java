package com.msb.han.juc.test01;

public class ThreadState {

    static class Mythread extends Thread{
        @Override
        public void run() {
            System.out.println(this.getState());
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(2000);
                    System.out.println(this.getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t=new Mythread();

        System.out.println(t.getState());

        t.start();

        System.out.println(t.getState());

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.getState());
    }
}
