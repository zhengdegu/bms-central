package com.gu.common.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author FastG
 * @create 2020-07-19
 **/
@ConditionalOnProperty(prefix = "spring.gu.security.oauth2",
        name = "store-type",
        havingValue = "redis",
        matchIfMissing = true)
@Configuration
public class RedisTokenStoreConfig {


    /**
     * redis连接工厂
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 使用redis存储token的配置，只有在imooc.security.oauth2.tokenStore配置为redis时生效
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(TokenStore.class)
    public TokenStore redisTokenStore() {
        return new org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore(redisConnectionFactory);
    }

}
