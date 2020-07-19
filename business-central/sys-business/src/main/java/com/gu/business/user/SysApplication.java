package com.gu.business.user;

import com.gu.redis.AutoRedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * @author FastG
 * @date 2020/7/17 14:35
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SysApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class, args);
    }
}
