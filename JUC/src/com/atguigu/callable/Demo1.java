package com.atguigu.callable;

//比较两个接口

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable{

    @Override
    public Object call() throws Exception {
        return 200;
    }
}
public class Demo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Runnable接口创建线程
        new Thread(new MyThread1(),"AA").start();

        //Callable接口
        //FutureTask
        FutureTask<Integer> futureTask1=new FutureTask<>(new MyThread2());

        //lam表达式
        FutureTask<Integer> futureTask2=new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName());
            return 1024;
        });

        //创建一个线程
        new Thread(futureTask2,"lucy").start();

        while (!futureTask2.isDone()){
            System.out.println("wait.....");
        }
        //调用FutureTask的get方法
        System.out.println(futureTask2.get());

        System.out.println(Thread.currentThread().getName()+" over");
    }
}
