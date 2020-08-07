package com.gu.feign;

import com.gu.common.constat.CommonConstants;
import com.sun.org.apache.regexp.internal.RE;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * @author FastG
 * @date 2020/8/7 9:41
 */
@Configuration
public class FeignInterceptorConfig {


    protected List<String> requestHeaders = new ArrayList<>();
    /**
     * Feign 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @PostConstruct
    public void initialize() {
       requestHeaders.add(CommonConstants.TOKEN_HEADER);
       requestHeaders.add(CommonConstants.USER_HEADER);
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        return  template ->{
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            HttpServletRequest request = servletRequestAttributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                String headerName;
                String headerValue;
                while(headerNames.hasMoreElements()) {
                    headerName = headerNames.nextElement();
                    if (requestHeaders.contains(headerName)) {
                        headerValue = request.getHeader(headerName);
                        template.header(headerName, headerValue);
                    }
                }
            }
        };
    }
}
