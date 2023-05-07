package com.atguigu.rabbitmq.five;

import com.atguigu.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author WenZK
 * @create 2023-05-05
 *
 * 消息接收
 */
public class ReceiveLogs02 {

    //交换机名称
    public static final String EXCHANGE_NAME="logs";

    public static void main(String[] args) throws Exception{
        Channel channel= RabbitMQUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //声明一个临时队列
        /**
         * 生成一个临时队列，名称随机
         * 当消费者断开与队列的连接的时候 队列自动删除
         */
        String queue = channel.queueDeclare().getQueue();
        /**
         * 绑定交换机与队列
         */
        channel.queueBind(queue,EXCHANGE_NAME,"");
        System.out.println("等待接收消息，把接收消息打印...");
        //接收消息
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println("02打印的消息："+new String(message.getBody(),"UTF-8"));
        };
        //消费者取消消息

        channel.basicConsume(queue,true,deliverCallback,consumerTag->{});
    }

}
