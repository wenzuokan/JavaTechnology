package com.atguigu.rabbitmq.three;

import com.atguigu.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * @author WenZK
 * @create 2023-05-05
 *
 * 消息在手动应答时是不丢失的，放回队列中重新消费
 */
public class Task2 {

    public static final String TASK_QUEUE_NAME="ack_queue";

    public static void main(String[] args) throws Exception{
        Channel channel= RabbitMQUtils.getChannel();

        channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);

        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String message=scanner.next();
            channel.basicPublish("",TASK_QUEUE_NAME,null,message.getBytes("UTF-8"));
            System.out.println("生产者发出消息："+message);
        }
    }
}
