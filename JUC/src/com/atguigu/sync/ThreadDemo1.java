package com.atguigu.sync;

//第一步  创建资源类，定义属性和操作方法
class Share{

    private int number=0;

    //+1方法
    public synchronized void incr() throws InterruptedException {
        //第二步  判断 干活 通知
        if (number!=0){
            this.wait();//哪里睡，哪里醒
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        //通知其他线程
        this.notifyAll();
    }
    //-1方法
    public synchronized void decr() throws InterruptedException {
        if (number!=1){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        this.notifyAll();
    }
}
public class ThreadDemo1 {

    public static void main(String[] args) {
        //第三步  创建多个线程，调用资源类的操作方法
        Share share=new Share();

        new Thread(()->{
            for (int i=1;i<=10;i++){
                try {
                    share.incr();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(()->{
            for (int i=1;i<=10;i++){
                try {
                    share.decr();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}
