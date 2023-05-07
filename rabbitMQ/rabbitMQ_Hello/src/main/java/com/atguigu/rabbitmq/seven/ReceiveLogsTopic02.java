package com.atguigu.rabbitmq.seven;

import com.atguigu.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author WenZK
 * @create 2023-05-06
 *
 * 主题交换机
 */
public class ReceiveLogsTopic02 {

    public static final String EXCHANGE_NAME="topic_logs";

    public static void main(String[] args) throws Exception{
        Channel channel= RabbitMQUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String queue="Q2";
        channel.queueDeclare(queue,false,false,false,null);
        channel.queueBind(queue,EXCHANGE_NAME,"*.*.rabbit");
        channel.queueBind(queue,EXCHANGE_NAME,"lazy.#");
        System.out.println("等待接收消息.....");
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println(new String(message.getBody(),"UTF-8"));
            System.out.println("接收队列"+queue+" 绑定键："+message.getEnvelope().getRoutingKey());
        };
        channel.basicConsume(queue,true,deliverCallback,consumerTag->{});
    }

}
