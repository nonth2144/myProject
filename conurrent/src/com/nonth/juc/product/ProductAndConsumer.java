package com.nonth.juc.product;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shkstart
 * @create 2019-05-30 15:30
 */

class ShareData
{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //1、判断
            while (number != 0)
            {
                //等待不能生产
                condition.await();
            }
            //2、干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3、唤醒
            condition.signalAll();

        } finally {
            lock.unlock();
        }
    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //1、判断
            while (number == 0)
            {
                //等待不能生产
                condition.await();
            }
            //2、干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3、唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * @author 21447
 */
public class ProductAndConsumer {

    public static void main(String[] args)
    {
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();

    }

}
