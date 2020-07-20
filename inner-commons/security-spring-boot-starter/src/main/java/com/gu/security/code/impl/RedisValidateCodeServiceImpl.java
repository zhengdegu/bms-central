package com.gu.security.code.impl;

import com.gu.common.properties.SecurityConstants;
import com.gu.common.properties.SecurityProperties;
import com.gu.redis.repository.RedisRepository;
import com.gu.security.code.ValidateCodeException;
import com.gu.security.code.ValidateCodeSevice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 基于redis的验证码存取器，避免由于没有session导致无法存取验证码的问题
 */
@Slf4j
public class RedisValidateCodeServiceImpl implements ValidateCodeSevice {

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 保存用户验证码，和randomStr绑定
     *
     * @param deviceId  客户端生成
     * @param imageCode 验证码信息
     */
    @Override
    public void saveImageCode(String deviceId, String imageCode) {
        if (log.isDebugEnabled()) {
            log.debug("设备id{}校验码{}", deviceId, imageCode);
        }
        redisRepository.setExpire(buildKey(deviceId), imageCode, securityProperties.getValidateCode().getLoginCode().getExpiration());
    }


    /**
     * 获取验证码
     *
     * @param deviceId 前端唯一标识/手机号
     */
    @Override
    public String getCode(String deviceId) {

        return (String) redisRepository.get(buildKey(deviceId));
    }

    /**
     * 删除验证码
     *
     * @param deviceId 前端唯一标识/手机号
     */
    @Override
    public void remove(String deviceId) {

        redisRepository.del(buildKey(deviceId));
    }

    /**
     * 验证验证码
     */
    @Override
    public void validate(String deviceId, String validCode) {
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("请在请求参数中携带deviceId参数");
        }
        String code = this.getCode(deviceId);
        if (StringUtils.isBlank(validCode)) {
            throw new ValidateCodeException("请填写验证码");
        }

        if (code == null) {
            throw new ValidateCodeException("验证码不存在或已过期");
        }

        if (!StringUtils.equals(code, validCode.toLowerCase())) {
            throw new ValidateCodeException("验证码不正确");
        }

        this.remove(deviceId);
    }

    private String buildKey(String deviceId) {
        return SecurityConstants.DEFAULT_CODE_KEY + ":" + deviceId;
    }
}
