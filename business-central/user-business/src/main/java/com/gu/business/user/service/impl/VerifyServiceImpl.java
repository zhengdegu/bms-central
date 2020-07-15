package com.gu.business.user.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.gu.business.user.service.VerifyService;
import com.gu.common.domain.vo.EmailVo;
import com.gu.common.exception.BadRequestException;
import com.gu.redis.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
public class VerifyServiceImpl implements VerifyService {

    @Value("${code.expiration}")
    private Long expiration;

    @Autowired
    private RedisRepository  redisRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EmailVo sendEmail(String email, String key) {
        EmailVo emailVo;
        String content;
        String redisKey = key + email;
        // 如果不存在有效的验证码，就创建一个新的
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email/email.ftl");
        Object oldCode =  redisRepository.get(redisKey);
        if(oldCode == null){
            String code = RandomUtil.randomNumbers (6);
            // 存入缓存
            if(!redisRepository.set(redisKey, code, expiration)){
                throw new BadRequestException("服务异常，请联系网站负责人");
            }
            content = template.render(Dict.create().set("code",code));
            emailVo = new EmailVo(Collections.singletonList(email),"后台管理系统",content);
        // 存在就再次发送原来的验证码
        } else {
            content = template.render(Dict.create().set("code",oldCode));
            emailVo = new EmailVo(Collections.singletonList(email),"后台管理系统",content);
        }
        return emailVo;
    }

    @Override
    public void validated(String key, String code) {
        Object value = redisRepository.get(key);
        if(value == null || !value.toString().equals(code)){
            throw new BadRequestException("无效验证码");
        } else {
            redisRepository.del(key);
        }
    }
}
