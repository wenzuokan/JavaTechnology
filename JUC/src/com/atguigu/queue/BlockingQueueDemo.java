package com.atguigu.queue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        //创建阻塞队列
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);

        //第一组
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.element());

        //System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        //第二组
        System.out.println(blockingQueue.offer("q"));
        System.out.println(blockingQueue.offer("w"));
        System.out.println(blockingQueue.offer("e"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        //第三组
        blockingQueue.put("z");

        System.out.println(blockingQueue.take());

    }

}
