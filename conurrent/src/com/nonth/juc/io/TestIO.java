package com.nonth.juc.io;

import org.junit.Test;

import java.io.*;
import java.nio.file.Paths;

/**
 * @author shkstart
 * @create 2019-05-23 17:03
 */

public class TestIO {


    public static void main(String[] args) {
        File file = new File("src\\hello.text");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getAbsoluteFile());

        try {
            FileReader fileReader = new FileReader(file);
                int read = fileReader.read();
                while (read!=-1){
                    System.out.println(fileReader.read());
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void test1(){
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }

}
/**
 * 1、构造器私有化
 * 2、自行创建，并且用静态变量保存
 * 3、向外提供这个实例
 */
class Singleton1{
    /**
     * 自行创建用静态变量保存
     */
    public static final Singleton1 SINGLETON1 = new Singleton1();

    /**
     * 构造器私有化
     */
    private Singleton1(){

    }

    public static void main(String[] args) {
    }


}