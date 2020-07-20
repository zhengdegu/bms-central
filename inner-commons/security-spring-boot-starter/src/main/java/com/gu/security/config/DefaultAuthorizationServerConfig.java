package com.gu.security.config;

import cn.hutool.core.collection.CollectionUtil;
import com.gu.common.properties.SecurityProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;


/**
 * 认证服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class DefaultAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // 如果jwt转换器和jwt增强器都存在
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            // 将其加入到增强链上
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();

            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);

            enhancerChain.setTokenEnhancers(enhancers);

            endpoints.tokenEnhancer(enhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
        endpoints
                // refresh_token需要userDetailsService
                .reuseRefreshTokens(true)
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }


    /**
     * 客户端配置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();

        if (CollectionUtil.isNotEmpty(securityProperties.getOauth2().getClients())){
            for (SecurityProperties.OAuth2ClientProperties client : securityProperties.getOauth2().getClients()) {
                builder.withClient(client.getClientId())
                        .secret(client.getClientSecret())
                        .accessTokenValiditySeconds(client.getAccessTokenValidateSeconds())
                        .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                        // 设置刷新令牌的过期时间
                        .refreshTokenValiditySeconds(2592000)
                        .scopes("all", "write", "read");
            }
        }

    }


}
