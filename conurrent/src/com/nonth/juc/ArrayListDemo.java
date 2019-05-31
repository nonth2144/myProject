package com.nonth.juc;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shkstart
 * @create 2019-05-28 16:21
 */
public class ArrayListDemo {

    public static void main(String[] args) throws InterruptedException {
        Lock reentrantLock = new ReentrantLock(true);

        CountDownLatch latch = new CountDownLatch(3000);
        Instant start = Instant.now();
//        List<String> list1 = Collections.synchronizedList(new ArrayList<>());//1229-1356
        List<String> list1 = new CopyOnWriteArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 3000 ; i++) {
            new Thread(()->{
                list1.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName()+"\t"+list1);
                latch.countDown();
            },String.valueOf(i)).start();
        }
        latch.await();
        Instant end = Instant.now();
        Duration between = Duration.between(start, end);
        long l = between.toMillis();
        System.out.println(l);
    }
}
