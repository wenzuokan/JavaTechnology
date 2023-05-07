package com.atguigu.rabbitmq.eight;

import com.atguigu.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;


/**
 * @author WenZK
 * @create 2023-05-06
 *
 * 死信队列
 */
public class Consumer02 {


    //死信队列
    public static final String DEAD_QUEUE="dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel= RabbitMQUtils.getChannel();

        System.out.println("等待接收消息...");

        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println("02接收的消息："+new String(message.getBody(),"UTF-8"));
        };
        channel.basicConsume(DEAD_QUEUE,true,deliverCallback,consumerTag->{});

    }
}
