package com.gu.business.iot.config;

import com.gu.network.handlers.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author FastG
 * @date 2020/8/7 17:40
 */
@Configuration
public class NetworkConfig {

//    @Bean
//    @Scope("prototype")
//    @ConditionalOnMissingBean(MessageDecoder.class)
//    public MessageDecoder messageDecoder() {
//
//        return new DefaultMessageDecoder();
//    }
//
//
//    @Bean
//    @ConditionalOnMissingBean(MessageEncoder.class)
//    public MessageEncoder messageEncoder() {
//        return new DefaultMessageEncoder();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(AuthReqHandler.class)
//    public AuthReqHandler loginAuthReqHandler() {
//        return new DefaultAuthReqHandler();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(MessageHandler.class)
//    public MessageHandler messageHandler() {
//        return new DefaultMessageHandler();
//    }
}
