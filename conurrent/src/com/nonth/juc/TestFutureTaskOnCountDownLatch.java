package com.nonth.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author shkstart
 * @create 2019-05-22 15:38
 */
public class TestFutureTaskOnCountDownLatch {

    public static void main(String[] args) {
        ThreadDemo1 threadDemo1 = new ThreadDemo1();
        long start = System.currentTimeMillis();
        int a = 4;

        FutureTask futureTask = new FutureTask(threadDemo1,a);
        for (int i = 0; i < 5; i++) {
            new Thread(futureTask).start();
        }
        long newStart = 0;
        try {
            Object o = futureTask.get();
            System.out.println(o);
//            newStart = (long) futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long i = end-newStart;
        System.out.println(i);
    }
}
class ThreadDemo1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}


