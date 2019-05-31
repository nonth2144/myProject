package com.nonth.juc;

/**
 * @author shkstart
 * @create 2019-05-22 21:01
 */
public class ThreadE {
    public static void main(String[] args) {

        Demo demo = new Demo();


        new Thread(()->{
            demo.get1();
        }).start();
        new Thread(()->{
            Demo.get2();
        }).start();
    }
}

class Demo{
    public  synchronized void get1(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1");
    }
    public  static synchronized void get2(){
        System.out.println("2");
    }
}
