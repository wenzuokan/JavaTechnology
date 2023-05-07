package com.atguigu.rabbitmq.utils;

/**
 * @author WenZK
 * @create 2023-05-05
 *睡眠工具类
 */
public class SleepUtils {

    public static void sleep(int second){
        try {
            Thread.sleep(1000*second);
        }catch (InterruptedException _ignored){
            Thread.currentThread().interrupt();
        }
    }
}
