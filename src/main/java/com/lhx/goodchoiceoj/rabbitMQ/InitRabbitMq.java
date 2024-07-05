package com.lhx.goodchoiceoj.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于创建测试程序用到的交换机和队列（只用在程序启动前执行一次）
 */
@Slf4j
public class InitRabbitMq {

    public static void doInit() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("106.53.68.162");
            factory.setPort(5672);  // 一般默认端口为5672
            factory.setUsername("LiangHaoxuan");
            factory.setPassword("Lhx20021030!");
            factory.setAutomaticRecoveryEnabled(true);  // 开启Connection自动恢复功能
            factory.setNetworkRecoveryInterval(5000);
            factory.setVirtualHost("GoodChoiceOjVHost");
            factory.setConnectionTimeout(30 * 1000);
            factory.setHandshakeTimeout(30 * 1000);
            factory.setShutdownTimeout(0);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String EXCHANGE_NAME = "code_exchange";
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            // 创建队列，随机分配一个队列名称
            String queueName = "code_queue";
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, EXCHANGE_NAME, "my_routingKey");
            log.info("消息队列启动成功");
        } catch (Exception e) {
            log.error("消息队列启动失败");
        }
    }

    public static void main(String[] args) {
        doInit();
    }
}
