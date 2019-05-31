package com.nonth.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author shkstart
 * @create 2019-05-29 22:44
 */
public class SinpLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "come on");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "myunlock.....");
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {

        SinpLockDemo sinpLockDemo = new SinpLockDemo();
        new Thread(() -> {
            sinpLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sinpLockDemo.myUnLock();
        }, "AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            sinpLockDemo.myLock();
            sinpLockDemo.myUnLock();
        }, "BB").start();

    }


}
