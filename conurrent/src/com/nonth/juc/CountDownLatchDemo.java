package com.nonth.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author shkstart
 * @create 2019-05-30 0:04
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"国");
                countDownLatch.countDown();
            }, CountryEnum.get(i).getRetMessage()).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------");
    }
}
