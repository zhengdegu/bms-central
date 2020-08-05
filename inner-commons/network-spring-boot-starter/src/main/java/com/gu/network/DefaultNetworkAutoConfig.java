package com.gu.network;


import com.gu.network.handlers.*;
import com.gu.network.properties.ServerProperties;
import com.gu.network.server.DefaultServer;
import com.gu.network.server.Server;
import com.gu.network.session.ConnectionManager;
import com.gu.network.session.MemoryConnectionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 * @author FastG
 * @date 2019/8/26 15:36
 */
@EnableConfigurationProperties({ServerProperties.class})
@Configuration
public class DefaultNetworkAutoConfig {

    @Bean
    @ConditionalOnMissingBean(IdleStateChecke.class)
    public IdleStateChecke idleStateChecke() {
        return new DefaultIdleStateChecke();
    }

    @Bean
    @ConditionalOnMissingBean(Server.class)
    public Server server(ServerProperties serverProperties, ServerChannelInitializer serverChannelInitializer) {
        return new DefaultServer(serverProperties, serverChannelInitializer);
    }


    @Bean
    @ConditionalOnMissingBean(ServerChannelInitializer.class)
    public ServerChannelInitializer serverChannelInitializer(ServerProperties serverProperties) {
        return new DefaultServerChannelInitialize(serverProperties);
    }


    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean(MessageDecoder.class)
    public MessageDecoder messageDecoder() {
        return new DefaultMessageDecoder();
    }


    @Bean
    @ConditionalOnMissingBean(MessageEncoder.class)
    public MessageEncoder messageEncoder() {
        return new DefaultMessageEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(AuthReqHandler.class)
    public AuthReqHandler loginAuthReqHandler() {
        return new DefaultAuthReqHandler();
    }

    @Bean
    @ConditionalOnMissingBean(MessageHandler.class)
    public MessageHandler messageHandler() {
        return new DefaultMessageHandler();
    }


    @Bean
    @ConditionalOnMissingBean(name = "memoryConnectionManager")
    public ConnectionManager connectionManager() {
        return new MemoryConnectionManager();
    }
}
