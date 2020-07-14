package com.gu.business.user.config;

import com.gu.common.config.DefaultPasswordConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author FastG
 * @date 2020/7/14 19:03
 */
@Configuration
@Import(DefaultPasswordConfig.class)
public class UserBusinessConfig {

}
