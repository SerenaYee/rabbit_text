package com.biz;
import com.rabbitmq.client.*;
import com.sun.org.apache.regexp.internal.RE;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Send
{
    //队列名称
    private static final String EXCHANGE_NAME = "hello";
    public static void sendMessage() throws IOException,TimeoutException
    //public static void main(String[] argv) throws IOException,TimeoutException
    {
        /**
         * 创建连接连接到MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        //设置MabbitMQ所在主机ip或者主机名
        factory.setHost("120.78.223.184");
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setPort(5672);
        //创建一个连接
        Connection connection = factory.newConnection();
        //创建一个频道
        Channel channel = connection.createChannel();
        //指定一个队列
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //发送的消息
        System.out.println("welcome weChat team！(input 'exit' out)");
        Scanner scan = new Scanner(System.in);
        String nickName;
        while(true){
            System.out.println("请输入昵称:");
             nickName=scan.nextLine();
            if(nickName.trim().length()>0){
                break;
            }
            System.out.println("昵称不能为空...");
        }
        while(true){
            String str=scan.nextLine();
            if("quit".equals(str)) {
                channel.close();
                connection.close();
                return;
            }
            str = nickName+":'" + str + "'";
            channel.basicPublish( EXCHANGE_NAME, "",null, str.getBytes());
        }
    }
}