package com.gu.common.properties;


import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@ConfigurationProperties(prefix = "spring.gu.security")
public class SecurityProperties {


    /**
     * 验证码配置
     */
    private ValidateCodeProperties validateCode = new ValidateCodeProperties();

    /**
     * OAuth2认证服务器配置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();
    /**
     * 配置需要放权的url白名单
     */
    private PermitProperties ignore = new PermitProperties();
    /**
     * oauth2配置
     */
    @Setter
    @Getter
    public static class OAuth2Properties {

        /**
         * 客户端配置
         */
        private List<SecurityProperties.OAuth2ClientProperties> clients =new ArrayList<>();

        /**
         * jwt的签名
         */
        private String jwtSigningKey = "gu";
        /**
         * token存储类型
         */
        private String storeType="redis";


    }

    /**
     * 认证服务器注册的第三方应用配置项
     */
    @Setter
    @Getter
    public static class OAuth2ClientProperties {

        /**
         * 第三方应用appId
         */
        private String client="gu";
        /**
         * 第三方应用appSecret
         */
        private String secret="gu";
        /**
         * 针对此应用发出的token的有效时间
         */
        private int access = 7200;

        /**
         * 刷新令牌的过期时间
         */
        private int refresh = 7200;
    }

    /**
     * 验证码配置
     * <p>
     * 图形验证码和短信验证码
     */
    @Setter
    @Getter
    public  static class ValidateCodeProperties {

        /**
         * 账号单用户 登录
         */
        private boolean singleLogin = false;

        private LoginCode loginCode = new LoginCode();
        /**
         * 用户登录信息缓存
         */
        private boolean cacheEnable;

        /**
         * 获取验证码生产类
         *
         * @return /
         */
        public Captcha getCaptcha() {
            if (Objects.isNull(loginCode)) {
                loginCode = new LoginCode();
                if (Objects.isNull(loginCode.getCodeType())) {
                    loginCode.setCodeType(LoginCodeEnum.arithmetic);
                }
            }
            return switchCaptcha(loginCode);
        }

        /**
         * 依据配置信息生产验证码
         *
         * @param loginCode 验证码配置信息
         * @return /
         */
        private Captcha switchCaptcha(LoginCode loginCode) {
            Captcha captcha;
            synchronized (this) {
                switch (loginCode.getCodeType()) {
                    case arithmetic:
                        // 算术类型 https://gitee.com/whvse/EasyCaptcha
                        captcha = new ArithmeticCaptcha(loginCode.getWidth(), loginCode.getHeight());
                        // 几位数运算，默认是两位
                        captcha.setLen(loginCode.getLength());
                        break;
                    case chinese:
                        captcha = new ChineseCaptcha(loginCode.getWidth(), loginCode.getHeight());
                        captcha.setLen(loginCode.getLength());
                        break;
                    case chinese_gif:
                        captcha = new ChineseGifCaptcha(loginCode.getWidth(), loginCode.getHeight());
                        captcha.setLen(loginCode.getLength());
                        break;
                    case gif:
                        captcha = new GifCaptcha(loginCode.getWidth(), loginCode.getHeight());
                        captcha.setLen(loginCode.getLength());
                        break;
                    case spec:
                        captcha = new SpecCaptcha(loginCode.getWidth(), loginCode.getHeight());
                        captcha.setLen(loginCode.getLength());
                        break;
                    default:
                        throw new RuntimeException("验证码配置信息错误！！！正确配置查看 me.zhengjie.modules.security.config.bean.LoginCodeEnum ");
                }
            }
            return captcha;
        }




        @Setter
        @Getter
        public class LoginCode {
            /**
             * 验证码配置
             */
            private LoginCodeEnum codeType= LoginCodeEnum.arithmetic;
            /**
             * 验证码有效期 秒
             */
            private Long expiration = 180L;
            /**
             * 验证码内容长度
             */
            private int length = 2;
            /**
             * 验证码宽度
             */
            private int width = 111;
            /**
             * 验证码高度
             */
            private int height = 36;


        }
    }

    /**
     * 配置需要放权的url白名单
     */
    @Setter
    @Getter
    public static class PermitProperties {
        /**
         * 监控中心和swagger需要访问的url
         */
        private final String[] ENDPOINTS = {
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

}
