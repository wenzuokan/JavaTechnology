package com.atguigu.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WenZK
 * @create 2023-05-06
 *
 * TTL队列
 */

@Configuration
public class TTLQueueConfig {

    //普通交换机
    public static final String X_EXCHANGE="X";
    //死信交换机
    public static final String Y_DEAD_LETTER_EXCHANGE="Y";
    //普通队列
    public static final String QUEUE_A="QA";
    public static final String QUEUE_B="QB";
    //死信队列
    public static final String DEAD_LETTER_QUEUE="QD";

    public static final String QUEUE_C="QC";//非TTL队列
    @Bean("queueC")
    public Queue queueC(){
        Map<String,Object> map=new HashMap<>(3);
        map.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);//设置死信交换机
        map.put("x-dead-letter-routing-key","YD");//设置死信RoutingKey
        return QueueBuilder.durable(QUEUE_C).withArguments(map).build();
    }
    @Bean
    public Binding queueCBinding(@Qualifier("queueC") Queue queueC,
                                 @Qualifier("xExchange" )DirectExchange xExchange){
        return BindingBuilder.bind(queueC).to(xExchange).with("XC");
    }

    //声明交换机
    @Bean("xExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }
    @Bean("yExchange")
    public DirectExchange yExchange(){
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    //声明队列
    @Bean("queueA")
    public Queue queueA(){
        Map<String,Object> map=new HashMap<>(3);
        map.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);//设置死信交换机
        map.put("x-dead-letter-routing-key","YD");//设置死信RoutingKey
        map.put("x-message-ttl",10000);//设置TTL
        return QueueBuilder.durable(QUEUE_A).withArguments(map).build();
    }
    @Bean("queueB")
    public Queue queueB(){
        Map<String,Object> map=new HashMap<>(3);
        map.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);//设置死信交换机
        map.put("x-dead-letter-routing-key","YD");//设置死信RoutingKey
        map.put("x-message-ttl",40000);//设置TTL
        return QueueBuilder.durable(QUEUE_B).withArguments(map).build();
    }
    @Bean("queueD")
    public Queue queueD(){
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    //绑定
    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queueA,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }
    @Bean
    public Binding queueBBindingX(@Qualifier("queueB") Queue queueB,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");
    }
    @Bean
    public Binding queueDBindingX(@Qualifier("queueD") Queue queueD,
                                  @Qualifier("yExchange") DirectExchange yExchange){
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }
}
