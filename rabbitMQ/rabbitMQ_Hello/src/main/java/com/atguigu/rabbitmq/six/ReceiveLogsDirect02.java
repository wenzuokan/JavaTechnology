package com.atguigu.rabbitmq.six;

import com.atguigu.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author WenZK
 * @create 2023-05-06
 *
 */
public class ReceiveLogsDirect02 {

    public static final String EXCHANGE_NAME="direct_logs";

    public static void main(String[] args) throws Exception{
        Channel channel= RabbitMQUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        channel.queueDeclare("disk",false,false,false,null);

        channel.queueBind("disk",EXCHANGE_NAME,"error");


        DeliverCallback deliverCallback=(consumerTag, message)->{
            System.out.println("02打印的消息："+new String(message.getBody(),"UTF-8"));
        };
        //消费者取消消息

        channel.basicConsume("disk",true,deliverCallback,consumerTag->{});
    }
}
