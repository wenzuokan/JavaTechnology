package com.atguigu.rabbitmq.two;

import com.atguigu.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author WenZK
 * @create 2023-05-05
 *
 * 这是一个工作线程
 */
public class Work01 {

    //队列名称
    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws Exception{
        Channel channel= RabbitMQUtils.getChannel();

        DeliverCallback deliverCallback=(consumerTag, message)->{
            System.out.println("接收到的消息："+new String(message.getBody()));
        };
        CancelCallback cancelCallback= consumerTag->{
            System.out.println(consumerTag+"消息消费中断");
        };
        System.out.println("C2等待接收....");
        //消息接收
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
