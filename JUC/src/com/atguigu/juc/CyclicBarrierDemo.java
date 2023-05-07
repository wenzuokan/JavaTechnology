package com.atguigu.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//集齐7个龙珠召唤神龙
public class CyclicBarrierDemo {

    private static final int NUMBER=7;

    public static void main(String[] args) {
        //创建CyclicBarrier
        CyclicBarrier cyclicBarrier=new CyclicBarrier(NUMBER,()->{
            System.out.println("%集齐7个龙珠召唤神龙");
        });

        //集齐7龙珠过程
        for (int i=1;i<=7;i++){
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+" 星龙珠找到了");
                    //等待
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
