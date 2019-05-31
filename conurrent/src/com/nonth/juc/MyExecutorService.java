package com.nonth.juc;

import java.util.concurrent.*;

/**
 * @author shkstart
 * @create 2019-05-30 20:22
 */
public class MyExecutorService {
    public static void main(String[] args)
    {
        int i1 = Runtime.getRuntime().availableProcessors();
        System.out.println(i1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, i1+1,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            for (int i = 1; i <= 10; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }finally {
            threadPoolExecutor.shutdown();
        }
    }
}
