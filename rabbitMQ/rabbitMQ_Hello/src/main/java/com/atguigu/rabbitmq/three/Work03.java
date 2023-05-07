package com.atguigu.rabbitmq.three;

import com.atguigu.rabbitmq.utils.RabbitMQUtils;
import com.atguigu.rabbitmq.utils.SleepUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;


/**
 * @author WenZK
 * @create 2023-05-05
 *
 * 消息在手动应答时不丢失，放回队列重新消费
 */
public class Work03 {

    public static final String TASK_QUEUE_NAME="ack_queue";

    public static void main(String[] args) throws Exception{
        Channel channel= RabbitMQUtils.getChannel();
        System.out.println("C1等待时间短");

        DeliverCallback deliverCallback=(consumerTag, message)->{
            SleepUtils.sleep(1);
            System.out.println("接收到的消息："+new String(message.getBody(),"UTF-8"));
            //手动应答
            /**
             * 1.消息的标记
             * 2.是否批量应答
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };

        //采用手动应答
        boolean autoAck=false;
        channel.basicConsume(TASK_QUEUE_NAME,autoAck,deliverCallback,(consumerTag->{
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        }));
    }

}
