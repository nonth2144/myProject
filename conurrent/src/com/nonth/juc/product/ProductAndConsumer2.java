package com.nonth.juc.product;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shkstart
 * @create 2019-05-30 15:30
 */
class ShareResoure {
    private volatile boolean flag = true;
    private AtomicInteger number = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public ShareResoure(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void pro() throws Exception {
        String data = null;
        boolean retValue;
        while (flag) {
            data = number.incrementAndGet() + "";

            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "插入队列" + data);
            } else {
                System.out.println("lose");
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "停止");
    }

    public void cons() throws Exception {
        String result = null;
        while (flag) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);

            if (result == null || result.equalsIgnoreCase("")) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "超时停止");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费队列" + result);

        }
    }

    public void stop() {
        this.flag = false;
    }


}

public class ProductAndConsumer2 {
    public static void main(String[] args) {

        ShareResoure shareResoure = new ShareResoure(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            System.out.println("生产线程启动");
            try {
                shareResoure.pro();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "product").start();
        new Thread(() -> {
            System.out.println("消费线程启动");
            try {
                shareResoure.cons();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();

         try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

         shareResoure.stop();
        System.out.println(Thread.currentThread().getName()+"停止");
    }


}
