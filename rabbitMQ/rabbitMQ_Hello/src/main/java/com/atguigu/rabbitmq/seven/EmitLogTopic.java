package com.atguigu.rabbitmq.seven;

import com.atguigu.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WenZK
 * @create 2023-05-06
 *
 */
public class EmitLogTopic {

    public static final String EXCHANGE_NAME="topic_logs";

    public static void main(String[] args) throws Exception{
        Channel channel= RabbitMQUtils.getChannel();

        Map<String,String> map=new HashMap<>();
        map.put("quick.orange.rabbit","被队列Q1Q2接收到");
        map.put("quick.orange.fox","被队列Q1接收到");
        map.put("lazy.brown.fox","被队列Q2接收到 ");
        map.put("lazy.pink.rabbit","虽然满足队列Q2的两个绑定但是只会被接收一次");
        map.put("quick.orange.male.rabbit","四个单词不匹配任何绑定会被丢弃");

        for (Map.Entry<String,String> mapKey:map.entrySet()){
            String routingKey = mapKey.getKey();
            String message=mapKey.getKey();
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes("UTF-8"));
            System.out.println("生产者消息发出："+message);
        }
    }
}
