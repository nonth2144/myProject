package com.nonth.juc;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 * @author shkstart
 * @create 2019-05-22 1:28
 */
public class TestCas {

    public static void main(String[] args) {
        final compareAndSwap cas = new compareAndSwap();
        long star = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = cas.getValue();
                    boolean b = cas.compareAndSet(expectedValue, (int) (Math.random() * 101));
                    System.out.println(b);
                }
            }).start();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-star);
    }
}
class compareAndSwap{
    private  int value;
    //获取内存值
    public  synchronized int getValue(){
        return value;
    }
    //比较
    public synchronized int compareAndSwap(int expectedValue, int newValue){
        int oldValue = value;
        if (oldValue == expectedValue){
            this.value = newValue;
        }
        return oldValue;
    }
    //设置
    public synchronized boolean compareAndSet (int expectedValue, int newValue){
        return expectedValue == compareAndSwap(expectedValue,newValue);
    }
}