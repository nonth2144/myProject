package com.nonth.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shkstart
 * @create 2019-05-22 19:45
 */
public class TestLockAndCon {
    public static void main(String[] args) {

        Pss ps = new Pss();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    ps.PrintA(i);
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    ps.PrintB(i);
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                   ps.PrintC(i);
                }
            }
        },"C").start();

    }
}

class Pss {


    private int number = 1;
    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private Condition c2 = lock.newCondition();


    public void PrintA(int f) {
        lock.lock();
        try {
            if (number != 1) {
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": \t " + i + "\t现在是第" + f);
            }
            number=2;
            c2.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            lock.unlock();
        }
    }
    public void PrintB(int f) {
        lock.lock();
        try {
            if (number != 2) {
                c2.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + ": \t " + i + "\t现在是第" + f);
            }
            number=3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            lock.unlock();
        }
    }
    public void PrintC(int f) {
        lock.lock();
        try {
            if (number != 3) {
                c3.await();
            }
            for (int i = 1; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName() + ": \t " + i + "\t现在是第" + f);
            }
            number=1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            lock.unlock();
        }

    }
}