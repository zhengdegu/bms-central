package com.gu.admin;

import com.gu.admin.properties.SelfNotifierProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author FastG
 * @date 2020/8/20 16:28
 */
@EnableConfigurationProperties(SelfNotifierProperties.class)
@EnableAdminServer
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
