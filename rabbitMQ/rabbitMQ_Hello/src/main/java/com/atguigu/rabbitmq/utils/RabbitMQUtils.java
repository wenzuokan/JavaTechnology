package com.atguigu.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author WenZK
 * @create 2023-05-05
 *
 * 连接工厂创建信道的工具类
 */
public class RabbitMQUtils {

    public static Channel getChannel() throws Exception{
        //创建工厂
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.245.100");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection connection=factory.newConnection();

        return connection.createChannel();
    }
}
