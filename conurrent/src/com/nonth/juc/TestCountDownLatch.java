package com.nonth.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author shkstart
 * @create 2019-05-22 15:18
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch downLatch = new CountDownLatch(5);
        LatchDemo latchDemo = new LatchDemo(downLatch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(latchDemo).start();
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        long i = end - start;
        System.out.println(i);
    }

}

class LatchDemo implements Runnable {

    private CountDownLatch downLatch;


    public LatchDemo(CountDownLatch downLatch) {
        this.downLatch = downLatch;
    }


    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5000; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            } finally {
                //递减
                downLatch.countDown();
            }
        }
    }
}