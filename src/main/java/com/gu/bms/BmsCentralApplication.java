package com.gu.bms;

import com.gu.bms.redis.properties.CacheManagerProperties;
import com.gu.bms.security.security.properties.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({SecurityProperties.class,
        RedisProperties.class,
        CacheManagerProperties.class})
public class BmsCentralApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmsCentralApplication.class, args);
    }

}
