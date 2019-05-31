package com.nonth.juc;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author shkstart
 * @create 2019-05-22 21:17
 */
public class TestExecutor {
//    private static int produceTaskSleepTime = 5;
//    private static int consumeTaskSleepTime = 5000;
//    private static int produceTaskMaxNumber = 20; //定义最大添加10个线程到线程池中
//
//    public static void main(String[] args) {
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3,
//                                     TimeUnit. SECONDS, new ArrayBlockingQueue<Runnable>(3),
//                      new ThreadPoolExecutor.DiscardOldestPolicy());
//    }
//}

    public static void main(String[] args) {
        Instant start = Instant.now();
        ExecutorDemo executorDemo = new ExecutorDemo();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);


        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(executorDemo);
        }
        poolExecutor.shutdown();

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }


}

class ExecutorDemo implements Runnable {


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "：" + new Random().nextInt(100));
    }
}
