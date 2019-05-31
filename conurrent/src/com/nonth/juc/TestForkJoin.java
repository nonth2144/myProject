package com.nonth.juc;

import org.junit.Test;

import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author shkstart
 * @create 2019-05-22 23:50
 */
public class TestForkJoin {
    public static void main(String[] args) {

    }
    @Test
    public void test1(){
        long reduce = LongStream.range(0L, 100000000L)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println(reduce);


    }
}

