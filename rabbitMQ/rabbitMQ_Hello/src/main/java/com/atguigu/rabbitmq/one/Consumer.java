package com.atguigu.rabbitmq.one;

import com.rabbitmq.client.*;

/**
 * @author WenZK
 * @create 2023-05-05
 *
 *
 * 消费者  接收消息
 */
public class Consumer {

    //队列名称
    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws Exception{
        //创建工厂
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.245.100");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection connection=factory.newConnection();

        Channel channel=connection.createChannel();

        //声明 接收消息
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println(new String(message.getBody()));
        };
        //取消消息时的回调
        CancelCallback cancelCallback=consumerTag->{
            System.out.println("消息消费中断");
        };
        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true自动 false手动
         * 3.消费者未成功消费的回调
         * 4.消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
