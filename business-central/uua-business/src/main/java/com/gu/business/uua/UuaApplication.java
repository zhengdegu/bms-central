package com.gu.business.uua;

import com.gu.common.properties.SecurityProperties;
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
@EnableConfigurationProperties({SecurityProperties.class})
public class UuaApplication {
    public static void main(String[] args) {
        SpringApplication.run(UuaApplication.class, args);
    }
}
