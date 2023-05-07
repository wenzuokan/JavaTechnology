package com.atguigu.rabbitmq.eight;

import com.atguigu.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

/**
 * @author WenZK
 * @create 2023-05-06
 *
 * 死信队列 生产者代码
 */
public class ProducerDead {

    //普通交换机
    public static final String NORMAL_EXCHANGE="normal_exchange";

    public static void main(String[] args) throws Exception{
        Channel channel= RabbitMQUtils.getChannel();
        //死信消息 设置TTL时间
        AMQP.BasicProperties properties=
                new AMQP.BasicProperties().builder().expiration("10000").build();

        for (int i = 1; i < 11; i++) {
            String message="info"+i;
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",null,message.getBytes());
        }
    }
}
