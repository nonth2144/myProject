package com.nonth.juc;

/**
 * 生产者和消费者案例
 * @author shkstart
 * @create 2019-05-22 16:03
 */
public class TestLock {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Product product = new Product(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(product,"生产者").start();
        new Thread(consumer,"消费者").start();
        new Thread(product,"生产者2").start();
        new Thread(consumer,"消费者2").start();
    }


}
class Clerk{
    private int product = 0;
    //进货
    public synchronized void get(){
        while (product>=1){
            System.out.println("产品已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println(Thread.currentThread().getName()+":"+ ++product);
            this.notifyAll();
    }
    //卖货
    public synchronized void sale(){
        while (product<=0){
            System.out.println("缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println(Thread.currentThread().getName()+":"+ --product);
            this.notifyAll();

    }
}
class Product implements Runnable {
    Clerk clerk = new Clerk();
    public Product(Clerk clerk) {
        this.clerk = clerk;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.get();
        }
    }
}
class Consumer implements Runnable{
    Clerk clerk = new Clerk();

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }

    }
}
