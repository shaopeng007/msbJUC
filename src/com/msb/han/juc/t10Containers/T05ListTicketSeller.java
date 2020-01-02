package com.msb.han.juc.t10Containers;

import java.util.ArrayList;
import java.util.List;

public class T05ListTicketSeller {

    static List<String> tickets=new ArrayList<>();
    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(tickets.size()>0){
                    System.out.println("销售了--"+tickets.remove(0));
                }
            }).start();
        }
    }
}
