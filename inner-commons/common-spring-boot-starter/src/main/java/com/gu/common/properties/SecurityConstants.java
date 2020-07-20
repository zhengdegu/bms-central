package com.gu.common.properties;

public interface SecurityConstants {

    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL = "/code";
    /**
     * 当请求需要身份认证时，默认跳转的url
     *
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
    /**
     * 默认的用户名密码登录请求处理url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
    /**
     * 默认的手机验证码登录请求处理url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 默认的OPENID登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_OPENID = "/authentication/openid";


    /**
     * 默认生成图形验证码过期时间
     */
    int DEFAULT_IMAGE_EXPIRE = 60;

    /**
     * 默认保存code的前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY";

    /**
     * 超级管理员用户名
     */
    String ADMIN_USER_NAME = "admin";
}