package com.gu.security.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gu.common.properties.SecurityConstants;
import com.gu.common.utils.ResponseUtil;
import com.gu.security.authorize.AuthorizeConfigManager;
import com.gu.security.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;


    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;


    @Autowired
    private TokenStore tokenStore;

    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;


    @Resource
    private OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler;



    @Autowired
    private LogoutHandler logoutHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl(SecurityConstants.LOGOUT_URL)
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(logoutSuccessHandler)
                .clearAuthentication(true);
        http
                //应用验证码安全配置
                .apply(validateCodeSecurityConfig)
                .and()
                .csrf().disable();
        // 引用默认配置
        authorizeConfigManager.config(http.authorizeRequests());
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore)
                .stateless(true)
                .authenticationEntryPoint(authenticationEntryPoint)
                .expressionHandler(expressionHandler)
                .accessDeniedHandler(oAuth2AccessDeniedHandler);
    }



    @Resource
    private ObjectMapper objectMapper;

    /**
     * 未登录，返回401
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> ResponseUtil.responseFailed(objectMapper, response, authException.getMessage());
    }

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

    /**
     * 处理spring security oauth 处理失败返回消息格式
     */
    @Bean
    public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler() {

            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {
                ResponseUtil.responseFailed(objectMapper, response, authException.getMessage());
            }
        };
    }
}