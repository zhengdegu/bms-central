package com.gu.security.config;

import com.gu.common.properties.SecurityProperties;
import com.gu.security.code.ValidateCodeSevice;
import com.gu.security.code.impl.RedisValidateCodeServiceImpl;
import com.gu.security.service.DefaultUserDetailServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * @author FastG
 * @create 2020-07-17
 **/
@EnableConfigurationProperties({SecurityProperties.class})
@Configuration
public class DefaultAuthConfig {



    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService userDetailsService() {
        return new DefaultUserDetailServiceImpl();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public ValidateCodeSevice validateCodeSevice(){
        return new RedisValidateCodeServiceImpl();
    }
}
