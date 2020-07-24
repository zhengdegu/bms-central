package com.gu.security.config;

import com.gu.common.config.DefaultPasswordConfig;
import com.gu.common.properties.SecurityProperties;
import com.gu.security.authorize.DefaultLogoutHandler;
import com.gu.security.authorize.DefaultLogoutSuccessHandler;
import com.gu.security.code.ValidateCodeSevice;
import com.gu.security.code.impl.RedisValidateCodeServiceImpl;
import com.gu.security.service.DefaultRbacServiceImpl;
import com.gu.security.service.DefaultUserDetailServiceImpl;
import com.gu.security.service.RbacService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author FastG
 * @create 2020-07-17
 **/
@EnableConfigurationProperties({SecurityProperties.class})
@Configuration
@Import(DefaultPasswordConfig.class)
public class DefaultAuthConfig {


    @ConditionalOnMissingBean(LogoutHandler.class)
    @Bean
    public LogoutHandler logoutHandler() {
        return new DefaultLogoutHandler();
    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new DefaultLogoutSuccessHandler();
    }

    @Bean
    @ConditionalOnMissingBean(RbacService.class)
    public RbacService rbacService() {
        return new DefaultRbacServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService userDetailsService() {
        return new DefaultUserDetailServiceImpl();
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    public ValidateCodeSevice validateCodeSevice() {
        return new RedisValidateCodeServiceImpl();
    }
}
