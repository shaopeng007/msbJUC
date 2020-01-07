package com.msb.han.juc.t13ThreadPool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T05CompletableFuture {

    public static void main(String[] args) {
        long start,end;
        start=System.currentTimeMillis();

//        CompletableFuture<Double> futureTM= CompletableFuture.supplyAsync(()->priceOfTM());
//        CompletableFuture<Double> futureTB= CompletableFuture.supplyAsync(()->priceOfTB());
//        CompletableFuture<Double> futureJD= CompletableFuture.supplyAsync(()->priceOfJD());
//        CompletableFuture.allOf(futureTM,futureTB,futureJD).join();
//
//        end = System.currentTimeMillis();
//        System.out.println("use completable future! " + (end - start));


        CompletableFuture.supplyAsync(()->priceOfTM())
                .thenApply(String::valueOf)
                .thenApply(str-> "price " + str)
                .thenAccept(System.out::println);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double priceOfTM(){
        delay();
        return 1.00;
    }

    private static double priceOfTB(){
        delay();
        return 2.00;
    }

    private static double priceOfJD(){
        delay();
        return 3.00;
    }
    private static void delay(){
        int time=new Random().nextInt(500);
        try {
            TimeUnit.MICROSECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep\n",time);
    }
}
