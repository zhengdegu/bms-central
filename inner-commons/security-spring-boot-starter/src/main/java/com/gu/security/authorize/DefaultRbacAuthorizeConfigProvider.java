package com.gu.security.authorize;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author FastG
 * @create 2020-07-19
 **/
@Order(Integer.MIN_VALUE)
@Component
public class DefaultRbacAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config
                .antMatchers(HttpMethod.GET,
                        "/**/*.html",
                        "/hello",
                        "/resource").hasRole("ADMIN");
//                .anyRequest()
//                .access("");
        return true;
    }
}
