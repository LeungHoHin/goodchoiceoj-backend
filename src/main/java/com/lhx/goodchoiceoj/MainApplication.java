package com.lhx.goodchoiceoj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.lhx.goodchoiceoj.rabbitMQ.InitRabbitMq;

/**
 * 主类（项目启动入口）
 *
 * @author 梁浩轩
 */
@SpringBootApplication
@MapperScan("com.lhx.goodchoiceoj.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class MainApplication {

    public static void main(String[] args) {
//        InitRabbitMq.doInit();
        SpringApplication.run(MainApplication.class, args);
    }

}
