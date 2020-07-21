package com.gu.business.uua.config;
import com.gu.business.uua.service.impl.UserDetailsServiceImpl;
import com.gu.common.config.DefaultPasswordConfig;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author FastG
 * @date 2020/7/21 9:20
 */
@Configuration
@Import(DefaultPasswordConfig.class)
public class AuthConfig {
    /**
     * Feign 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    //覆盖 userdetailservice
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
}
