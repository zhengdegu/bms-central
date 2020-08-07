package com.gu.business.iot;

import com.gu.network.server.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author FastG
 * @date 2020/8/5 16:15
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class IotApplication {
    public static void main(String[] args) {
        Server server = SpringApplication.run(IotApplication.class, args).getBean(Server.class);
        // 添加关闭处理，更为优雅的关闭TCP服务
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                log.info("关闭TCP服务");
                server.stop();
            }
        });
        log.debug("Hello world");
        server.start();
    }

}
