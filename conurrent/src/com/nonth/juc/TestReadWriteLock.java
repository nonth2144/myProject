package com.nonth.juc;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shkstart
 * @create 2019-05-22 20:25
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLockDemo.set((int) (Math.random()*101));
            }
        },"写").start();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readWriteLockDemo.get();
                }
            }).start();
        }


    }
}
class ReadWriteLockDemo{
    private int number = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get(){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+":"+number);

        }finally {
            lock.readLock().unlock();
        }
    }
    public void set(int number){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+number);
            this.number = number;
        }finally {
            lock.writeLock().unlock();
        }
    }
}