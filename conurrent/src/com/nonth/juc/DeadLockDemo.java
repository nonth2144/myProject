package com.nonth.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author shkstart
 * @create 2019-05-30 22:37
 */
class HoldLockThread implements Runnable
{
    String lockA;
    String lockB;
    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA)
        {
            System.out.println(Thread.currentThread().getName()+"\t 自己有的："
            +lockA+"尝试获得："+lockB);
             try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB)
            {
                System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockB
                +"尝试获得："+lockA);
            }

        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args)
    {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"AAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"BBBB").start();
    }
}
