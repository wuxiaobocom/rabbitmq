package com.bobo.rabbitmq1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

/**
 * 消息队列生产者,这个例子只是简单的通过queue使用，
 * 所以是exchange是“”
 * @author wuxiaobo@didachuxing.com
 * @create 2018-11-04 13:36
 **/
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",false,false,false,null);
        String message = "hello world";
        for (int i =0 ;i <100000000;i++) {
            channel.basicPublish("","hello",null,message.getBytes(Charset.forName("UTF-8")));
        }
        System.out.println(" [x] Sent '" + message + "'");
        //关闭连接
        channel.close();
        connection.close();
    }
}
