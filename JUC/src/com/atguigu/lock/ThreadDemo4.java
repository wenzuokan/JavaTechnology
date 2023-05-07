package com.atguigu.lock;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ThreadDemo4 {

    public static void main(String[] args) {
        //创建ArrayList集合
        //List<String> list=new ArrayList<>();

        //Vector解决线程安全问题
        //List<String> list=new Vector<>();

        //Collections解决
        //List<String> list= Collections.synchronizedList(new ArrayList<>());

        List<String> list=new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                //向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }

        Set<String> set=new CopyOnWriteArraySet<>();

       // HashMap<String,String> hashMap=new ConcurrentHashMap<>();
    }
}
