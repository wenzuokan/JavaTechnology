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
public class Consumer01 {

    //普通交换机
    public static final String NORMAL_EXCHANGE="normal_exchange";
    //死信交换机
    public static final String DEAD_EXCHANGE="dead_exchange";

    //普通队列
    public static final String NORMAL_QUEUE="normal_queue";
    //死信队列
    public static final String DEAD_QUEUE="dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel= RabbitMQUtils.getChannel();

        //声明交换机
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE,BuiltinExchangeType.DIRECT);
        //声明队列
        Map<String,Object> arguments=new HashMap<>();
        //过期时间
       // arguments.put("x-message-ttl",100000);
        //正常队列设置死信交换机
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        //设置死信RoutingKey
        arguments.put("x-dead-letter-routing-key","list");
        //设置正常队列长度限制
       // arguments.put("x-max-length",6);
        channel.queueDeclare(NORMAL_QUEUE,false,false,false,arguments);

        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);

        //绑定普通交换机与队列
        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"zhangsan");
        //绑定死信交换机与死信队列
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"list");
        System.out.println("等待接收消息...");

        DeliverCallback deliverCallback=(consumerTag,message)->{
            String msg=new String(message.getBody(),"UTF-8");
            if (msg.equals("info5")){
                System.out.println(msg+":此消息被拒绝");
                channel.basicReject(message.getEnvelope().getDeliveryTag(),false);
            }else {
                System.out.println("01接收的消息：" + msg);
                channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
            }
        };
        //开启手动应答
        channel.basicConsume(NORMAL_QUEUE,false,deliverCallback,consumerTag->{});

    }
}
