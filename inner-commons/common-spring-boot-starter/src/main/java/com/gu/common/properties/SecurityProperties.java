package com.gu.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FastG
 * @date 2020/5/29 14:51
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "gu.security")
public class SecurityProperties {

    private PermitProperties ignore = new PermitProperties();
    private AuthProperties auth = new AuthProperties();
    private RsaProperties rsa = new RsaProperties();
    @Setter
    @Getter
    public static class PermitProperties {
        /**
         * 监控中心和swagger需要访问的url
         */
        private static final String[] ENDPOINTS = {
                "/oauth/**",
                "/actuator/**",
                "/*/v2/api-docs",
                "/swagger/api-docs",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**",
                "/druid/**"
        };

        /**
         * 设置不用认证的url
         */
        private String[] httpUrls = {};

        public String[] getUrls() {
            if (httpUrls == null || httpUrls.length == 0) {
                return ENDPOINTS;
            }
            List<String> list = new ArrayList<>();
            for (String url : ENDPOINTS) {
                list.add(url);
            }
            for (String url : httpUrls) {
                list.add(url);
            }
            return list.toArray(new String[list.size()]);
        }
    }


    @Setter
    @Getter
    public class AuthProperties {
        /**
         * 配置要认证的url（默认不需要配置）
         * <p>
         * 优先级大于忽略认证配置`zlt.security.ignore.httpUrls`
         * 意思是如果同一个url同时配置了`忽略认证`和`需要认证`，则该url还是会被认证
         */
        private String[] httpUrls = {};

        /**
         * token自动续签配置（目前只有redis实现）
         */
        private RenewProperties renew = new RenewProperties();

        /**
         * url权限配置
         */
        private UrlPermissionProperties urlPermission = new UrlPermissionProperties();
    }

    @Setter
    @Getter
    public class RenewProperties {
        /**
         * 是否开启token自动续签（目前只有redis实现）
         */
        private Boolean enable = false;

        /**
         * 白名单，配置需要自动续签的应用id（与黑名单互斥，只能配置其中一个），不配置默认所有应用都生效
         * 配置enable为true时才生效
         */
        private List<String> includeClientIds = new ArrayList<>();

        /**
         * 黑名单，配置不需要自动续签的应用id（与白名单互斥，只能配置其中一个）
         * 配置enable为true时才生效
         */
        private List<String> exclusiveClientIds = new ArrayList<>();

        /**
         * 续签时间比例，当前剩余时间小于小于过期总时长的50%则续签
         */
        private Double timeRatio = 0.5;
    }

    @Setter
    @Getter
    public class RsaProperties {

        public String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEA0vfvyTdGJkdbHkB8mp0f3FE0GYP3AYPaJF7jUd1M0XxFSE2ceK3k2kw20YvQ09NJKk+OMjWQl9WitG9pB6tSCQIDAQABAkA2SimBrWC2/wvauBuYqjCFwLvYiRYqZKThUS3MZlebXJiLB+Ue/gUifAAKIg1avttUZsHBHrop4qfJCwAI0+YRAiEA+W3NK/RaXtnRqmoUUkb59zsZUBLpvZgQPfj1MhyHDz0CIQDYhsAhPJ3mgS64NbUZmGWuuNKp5coY2GIj/zYDMJp6vQIgUueLFXv/eZ1ekgz2Oi67MNCk5jeTF2BurZqNLR3MSmUCIFT3Q6uHMtsB9Eha4u7hS31tj1UWE+D+ADzp59MGnoftAiBeHT7gDMuqeJHPL4b+kC+gzV4FGTfhR9q3tTbklZkD2A==";
    }

    @Setter
    @Getter
    public class UrlPermissionProperties {
        /**
         * 是否开启url级别权限
         */
        private Boolean enable = false;

        /**
         * 白名单，配置需要url权限认证的应用id（与黑名单互斥，只能配置其中一个），不配置默认所有应用都生效
         * 配置enable为true时才生效
         */
        private List<String> includeClientIds = new ArrayList<>();

        /**
         * 黑名单，配置不需要url权限认证的应用id（与白名单互斥，只能配置其中一个）
         * 配置enable为true时才生效
         */
        private List<String> exclusiveClientIds = new ArrayList<>();

        /**
         * 配置只进行登录认证，不进行url权限认证的api
         * 所有已登录的人都能访问的api
         */
        private String[] ignoreUrls = {};
    }

}
