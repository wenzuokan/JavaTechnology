package com.atguigu.pool;

import java.util.concurrent.*;

//演示线程池三种常用分类
public class ThreadPoolDemo1 {

    //int corePoolSize 常驻线程数量(核心)
    //int maximumPoolSize 最大线程数量
    //long keepAliveTime
    //TimeUnit unit      线程存活时间
    //BlockingQueue<Runnable> workQueue 阻塞队列
    //ThreadFactory threadFactory       线程工厂
    //RejectedExecutionHandler handler  拒绝策略


    public static void main(String[] args) {
        //一池五线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);

        //一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();

        //一池可扩容线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        try {
            //10个请求
            for (int i = 1; i <= 10; i++) {
                //执行
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool3.shutdown();
        }
    }
}
