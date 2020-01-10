package com.msb.han.juc.t13ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class T10FixedThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        getPrime(1,200000);
        long end=System.currentTimeMillis();
        System.out.println(end-start);

        final int cpuCoreNum=4;
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
        MyTask m1=new MyTask(1,50000);
        MyTask m2=new MyTask(50001,10000);
        MyTask m3=new MyTask(100001,150000);
        MyTask m4=new MyTask(150001,200000);

        Future<List<Integer>> s1 = service.submit(m1);
        Future<List<Integer>> s2 = service.submit(m2);
        Future<List<Integer>> s3 = service.submit(m3);
        Future<List<Integer>> s4 = service.submit(m4);

        start = System.currentTimeMillis();
        s1.get();
        s2.get();
        s3.get();
        s4.get();
        end=System.currentTimeMillis();
        System.out.println(end-start);

       service.shutdown();
    }

    static class MyTask implements Callable<List<Integer>>{
        int startPos,endPos;

        MyTask(int s,int e){
            this.startPos=s;
            this.endPos=e;
        }
        @Override
        public List<Integer> call() throws Exception {

            List<Integer> r=getPrime(startPos,endPos);
            return r;
        }
    }
    static boolean isPrime(int num){
        for(int i=2;i<=num/2;i++){
            if(num % i==0) return false;
        }
        return true;
    }

    static List<Integer> getPrime(int start,int end){
        List<Integer> result=new ArrayList<>();
        for (int i = start; i <=end ; i++) {
           if(isPrime(i)) result.add(i);
        }
        return result;
    }
}
