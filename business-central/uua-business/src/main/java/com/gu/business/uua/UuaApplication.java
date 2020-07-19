package com.gu.business.uua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 认证服务启动
 *
 * @author FastG
 * @date 2020/7/15 14:42
 */
@SpringBootApplication
public class UuaApplication {
    public static void main(String[] args) {
        SpringApplication.run(UuaApplication.class, args);
    }
}
