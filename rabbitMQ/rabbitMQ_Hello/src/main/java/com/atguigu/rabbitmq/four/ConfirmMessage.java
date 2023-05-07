package com.atguigu.rabbitmq.four;

import com.atguigu.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author WenZK
 * @create 2023-05-05
 *
 * 发布确认模式
 * 1.单个确认
 * 2.批量确认
 * 3.异步批量确认
 */
public class ConfirmMessage {

    //批量发消息的个数
    public static final int MESSAGE_COUNT=1000;

    public static void main(String[] args) throws Exception{
        //1.单个确认
        //ConfirmMessage.publishMessageIndividually();//发布1000个单独确认消息耗时：1318ms
        //2.批量确认
        //ConfirmMessage.publishMessageBatch();//发布1000批量确认消息耗时：113ms
        //3.异步批量确认
        ConfirmMessage.publishMessageAsync();//发布1000异步批量确认消息耗时：40ms
    }

    //单个
    public static void publishMessageIndividually()throws Exception{
        Channel channel= RabbitMQUtils.getChannel();

        String queueName= UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);
        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long begin=System.currentTimeMillis();

        //批量发消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message=i+"";
            channel.basicPublish("",queueName,null,message.getBytes());
            //单个消息马上进行发布
            boolean flag=channel.waitForConfirms();
            if (flag){
                System.out.println("消息发布成功");
            }
        }
        //结束时间
        long end=System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"个单独确认消息耗时："+(end-begin)+"ms");
    }

    //批量
    public static void publishMessageBatch() throws Exception{
        Channel channel= RabbitMQUtils.getChannel();

        String queueName= UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);
        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long begin=System.currentTimeMillis();
        //批量确认消息大小
        int batchSize=100;
        //批量发消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());
            if (i%batchSize==0){
                channel.waitForConfirms();
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"批量确认消息耗时："+(end-begin)+"ms");
    }

    //异步批量
    public static void publishMessageAsync() throws Exception{
        Channel channel= RabbitMQUtils.getChannel();

        String queueName= UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);
        //开启发布确认
        channel.confirmSelect();
        /**
         * 线程安全有序的一个哈希表 适用于高并发的情况下
         * 1.轻松的将序号与消息关联
         * 2.轻松的批量删除条目 只要给到序号
         * 3.支持高并发（多线程）
         */
        ConcurrentSkipListMap<Long,String> outstandingConfirms=new ConcurrentSkipListMap<>();

        //开始时间
        long begin=System.currentTimeMillis();
        //准备消息的监听器 监听哪些消息成功了 哪些失败了
        ConfirmCallback ack=(deliveryTag,multiple)->{
            //2.删除已经确认消息
            if (multiple){
                ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(deliveryTag);
                confirmed.clear();
            }else {
                outstandingConfirms.remove(deliveryTag);
            }
            System.out.println("确认的消息："+deliveryTag);
        };
        ConfirmCallback nack=(deliveryTag,multiple)->{
            String s = outstandingConfirms.get(deliveryTag);
            System.out.println("未确认消息："+s+"::+::"+deliveryTag);
        };
        channel.addConfirmListener(ack,nack);//异步通知
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message="消息"+i;
            channel.basicPublish("",queueName,null,message.getBytes());
            //1.记录所有要发送的消息 消息总和
            outstandingConfirms.put(channel.getNextPublishSeqNo(),message);
        }
        long end=System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"异步批量确认消息耗时："+(end-begin)+"ms");
    }
}
