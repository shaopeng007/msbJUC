package com.msb.han.juc.test03;
//双重检查单例，线程安全
public class Single_Double_Check {

    public static /*volatile*/   Single_Double_Check INSTANCE=null;
    // 有必要加volatile吗？有防止指令重排序

    private Single_Double_Check(){

    };

    public Single_Double_Check getInstance(){
        if (INSTANCE==null){
            //双重检查
         synchronized (Single_Double_Check.class){
             if (INSTANCE==null){
                 try {
                     Thread.sleep(1);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 INSTANCE=new Single_Double_Check();
                 /*
                 * new 一个对象JVM初始化分为3步，
                 * 1 申请一块内存如果有值 a=0(默认值)
                 * 2 给a复值a=8
                 * 3 将a=8 给对象引用
                 * */
             }
         }

        }
        return  INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < ; i++) {

        }
    }
}
