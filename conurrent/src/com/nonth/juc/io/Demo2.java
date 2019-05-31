package com.nonth.juc.io;


import java.util.concurrent.*;

/**
 * @author shkstart
 * @create 2019-05-24 20:23
 */
class MyData {
    int number = 0;
    public void addTO60() {
        this.number = 60;
    }

}

public class Demo2 {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3));

        MyData myData = new MyData();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");

        },"").start();

        new Thread(()->{

        },"name").start();

    }
}
