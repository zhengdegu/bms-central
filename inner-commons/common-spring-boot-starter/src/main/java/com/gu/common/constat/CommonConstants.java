package com.gu.common.constat;

/**
 * @author FastG
 * @date 2020/5/30 13:36
 */
public interface CommonConstants {

    /**
     * 默认密码
     */
    String DEFAULT_PASSWORD = "123456";
    /**
     * 项目版本号(banner使用)
     */
    String PROJECT_VERSION = "3.6.1";

    /**
     * token请求头名称
     */
    String TOKEN_HEADER = "Authorization";

    /**
     * The access token issued by the authorization server. This value is REQUIRED.
     */
    String ACCESS_TOKEN = "access_token";

    String BEARER_TYPE = "Bearer";

    /**
     * 标签 header key
     */
    String HEADER_LABEL = "x-label";

    /**
     * 标签 header 分隔符
     */
    String HEADER_LABEL_SPLIT = ",";

    /**
     * 标签或 名称
     */
    String LABEL_OR = "labelOr";

    /**
     * 标签且 名称
     */
    String LABEL_AND = "labelAnd";

    /**
     * 权重key
     */
    String WEIGHT_KEY = "weight";

    /**
     * 删除
     */
    String STATUS_DEL = "1";

    /**
     * 正常
     */
    String STATUS_NORMAL = "0";

    /**
     * 锁定
     */
    String STATUS_LOCK = "9";

    /**
     * 目录
     */
    Integer CATALOG = -1;

    /**
     * 菜单
     */
    Integer MENU = 1;

    /**
     * 权限
     */
    Integer PERMISSION = 2;

    /**
     * 删除标记
     */
    String DEL_FLAG = "is_del";

    /**
     * 超级管理员用户名
     */
    String ADMIN_USER_NAME = "admin";

    /**
     * 公共日期格式
     */
    String MONTH_FORMAT = "yyyy-MM";
    String DATE_FORMAT = "yyyy-MM-dd";
    String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String SIMPLE_MONTH_FORMAT = "yyyyMM";
    String SIMPLE_DATE_FORMAT = "yyyyMMdd";
    String SIMPLE_DATETIME_FORMAT = "yyyyMMddHHmmss";

    String DEF_USER_PASSWORD = "123456";

    String LOCK_KEY_PREFIX = "LOCK_KEY:";

    /**
     * 用户信息分隔符
     */
    String USER_SPLIT = ":";

    /**
     * 用户信息头
     */
    String USER_HEADER = "x-user-header";

    /**
     * 用户id信息头
     */
    String USER_ID_HEADER = "x-userid-header";

    /**
     * 角色信息头
     */
    String ROLE_HEADER = "x-role-header";

    /**
     * 登录页面
     */
    String LOGIN_PAGE = "/login.html";
    /**
     * 登录失败页面
     */
    String LOGIN_FAILURE_PAGE = LOGIN_PAGE + "?error";
    /**
     * 用于IP定位转换
     */
    public static final String REGION = "内网IP|内网IP";
    /**
     * win 系统
     */
    public static final String WIN = "win";

    /**
     * mac 系统
     */
    public static final String MAC = "mac";
    /**
     * 日志服务
     */
    String LOG_BUSINESS ="log-business" ;
    /**
     * 认证服务
     */
    String UUA_BUSINESS ="uua-business" ;
    /**
     * 常用接口
     */
    public static class Url {
        // 免费图床
        public static final String SM_MS_URL = "https://sm.ms/api";
        // IP归属地查询
        public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }
}
