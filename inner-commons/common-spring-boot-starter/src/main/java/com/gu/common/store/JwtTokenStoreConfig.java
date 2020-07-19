package com.gu.common.store;

import com.gu.common.jwt.DefaultJwtTokenEnhancer;
import com.gu.common.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author FastG
 * @create 2020-07-19
 **/
@ConditionalOnProperty(prefix = "spring.gu.security.oauth2",
        name = "store-type",
        havingValue = "jwt",
        matchIfMissing = true)
@Configuration
public  class JwtTokenStoreConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(TokenEnhancer.class)
    public TokenStore jwtTokenStore() {
        return new org.springframework.security.oauth2.provider.token.store.JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
        // 可以采用证书的方式
        return converter;
    }

    @Bean
    @ConditionalOnMissingBean(TokenEnhancer.class)
    public TokenEnhancer jwtTokenEnhancer() {
        return new DefaultJwtTokenEnhancer();
    }
}
