package com.gu.business.user.service;

import com.gu.common.domain.vo.EmailVo;

/**
 * @author FastG
 * @date 2020/7/15 14:17
 */
public interface VerifyService {

    /**
     * 发送验证码
     * @param email /
     * @param key /
     * @return /
     */
    EmailVo sendEmail(String email, String key);


    /**
     * 验证
     * @param code /
     * @param key /
     */
    void validated(String key, String code);
}
