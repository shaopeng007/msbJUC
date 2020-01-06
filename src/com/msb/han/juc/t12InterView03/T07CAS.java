package com.msb.han.juc.t12InterView03;

public class T07CAS {
    enum ReadyToRun {T1,T2};

    static volatile  ReadyToRun r=ReadyToRun.T1;

    public static void main(String[] args) {
        char [] charA="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int [] char1={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
        new Thread(()->{

            for (char c : charA) {
                while (r!=ReadyToRun.T1){}
                System.out.print(c);
                r=ReadyToRun.T2;
            }

        }).start();
        new Thread(()->{

            for (int c : char1) {
                while (r!=ReadyToRun.T2){}
                System.out.print(c);
                r=ReadyToRun.T1;
            }

        }).start();

    }
}
