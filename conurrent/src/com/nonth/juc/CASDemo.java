package com.nonth.juc;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shkstart
 * @create 2019-05-27 15:16
 */

class User{

    private String name;
    private int age;

}
public class CASDemo {





    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(5);

        int andIncrement = integer.getAndIncrement();
        System.out.println(andIncrement);

    }


}
