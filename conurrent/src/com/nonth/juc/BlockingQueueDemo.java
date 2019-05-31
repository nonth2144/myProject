package com.nonth.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author shkstart
 * @create 2019-05-30 14:04
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        //List list = new ArrayList();
        BlockingQueue<String> queue = new ArrayBlockingQueue(3);
//       System.out.println(queue.add("a"));
//       System.out.println(queue.add("b"));
//       System.out.println(queue.add("c"));
//
//       System.out.println(queue.remove());
//       System.out.println(queue.remove());
//       System.out.println(queue.remove());
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");

        System.out.println(queue.peek());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        BlockingQueue<String> blockingQueue = new SynchronousQueue();
        try {
            blockingQueue.put("a");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
