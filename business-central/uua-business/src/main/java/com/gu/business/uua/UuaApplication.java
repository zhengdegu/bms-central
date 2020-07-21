package com.gu.business.uua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 认证服务启动
 *
 * @author FastG
 * @date 2020/7/15 14:42
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.gu")
public class UuaApplication {
    public static void main(String[] args) {
        SpringApplication.run(UuaApplication.class, args);
    }
}
