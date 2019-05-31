package com.nonth.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性问题
 * @author shkstart
 * @create 2019-05-22 0:43
 */
public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }
}
class AtomicDemo implements Runnable{
//    private volatile int sn = 0;
    private AtomicInteger sn = new AtomicInteger();
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getSn());

    }
    public int getSn(){
//        sn.getAndDecrement()//获取并递减
        return sn.getAndIncrement();//获取并递增
    }
}