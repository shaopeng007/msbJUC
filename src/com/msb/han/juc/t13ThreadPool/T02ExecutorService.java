/**
 * ��ʶExecutorService,�Ķ�API�ĵ�
 * ��ʶsubmit��������չ��execute����������һ������ֵ
 */
package com.msb.han.juc.t13ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T02ExecutorService {
    public static void main(String[] args) {
        ExecutorService e = Executors.newCachedThreadPool();
    }
}
