package com.nonth.juc;

import java.time.Instant;

/**
 * @author shkstart
 * @create 2019-05-21 23:57
 */
public class TestVolatile {

    public static void main(String[] args) {

        Instant now = Instant.now();
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        while (true){
            if (td.isFlag()){
                System.out.println("-------------");
              break;
            }
        }
        Instant now1 = Instant.now();
        System.out.println(now1);


    }


}
class ThreadDemo implements  Runnable{
    private  volatile boolean flag = false;

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;

        System.out.println("flag =" +flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}