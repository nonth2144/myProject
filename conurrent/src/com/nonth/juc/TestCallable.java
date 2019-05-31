package com.nonth.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * @author shkstart
 * @create 2019-05-22 15:27
 */
public class TestCallable {
    public static void main(String[] args) {
        CallableTest callableTest = new CallableTest();
        FutureTask<Integer> futureTask = new FutureTask<>(callableTest);
        new Thread(futureTask).start();
        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class CallableTest implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            System.out.println(i);
            sum +=i;
        }
        return sum;
    }
}
