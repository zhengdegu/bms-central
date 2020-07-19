package com.gu.security.config;

import com.gu.common.jwt.DefaultJwtTokenEnhancer;
import com.gu.common.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
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
       // 存储在内存中
        clients.inMemory()
                // 设置clientid
                .withClient("gu")
                // 设置clientsecret
                .secret("gu")
                // 设置令牌过期时间
                .accessTokenValiditySeconds(7200)
                // 允许的授权模式
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                // 配置oauth能获取的权限
                .scopes("all")
                .and()
                // 第二个app
                .withClient("earthchen_web");
//        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
//        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
//            for (OAuth2ClientProperties config : securityProperties.getOauth2().getClients()) {
//                builder.withClient(config.getClientId())
//                        .secret(config.getClientSecret())
//                        .accessTokenValiditySeconds(config.getAccessTokenValidateSeconds())
//                        .authorizedGrantTypes("refresh_token", "authorization_code", "password")
//                        // 设置刷新令牌的过期时间
//                        .refreshTokenValiditySeconds(2592000)
//                        .scopes("all", "write", "read");
//            }
//        }
    }


}
