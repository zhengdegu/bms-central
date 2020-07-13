package com.gu.bms.security.controller;

import com.gu.bms.common.exception.BadRequestException;
import com.gu.bms.common.utils.RsaUtils;
import com.gu.bms.redis.utils.RedisUtil;
import com.gu.bms.security.security.JwtToken;
import com.gu.bms.security.security.properties.SecurityProperties;
import com.gu.bms.security.security.service.dto.AdminUserDetails;
import com.gu.bms.security.security.service.dto.AuthUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FastG
 * @create 2020-07-13
 **/
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "系统：系统授权接口")
public class UesrController {

    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private  AuthenticationManagerBuilder authenticationManagerBuilder;

    @Resource
    JwtToken jwtToken;
    @ApiOperation("登录授权")
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) throws Exception {
        // 密码解密
        String password = RsaUtils.decryptByPrivateKey(securityProperties.getRsa().getPrivateKey(), authUser.getPassword());
        // 查询验证码
        String code = (String) redisUtil.get(authUser.getUuid());
        // 清除验证码
        redisUtil.del(authUser.getUuid());
        if (StringUtils.isBlank(code)) {
            throw new BadRequestException("验证码不存在或已过期");
        }
        if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("验证码错误");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        String token = jwtToken.generateToken(authentication);
        AdminUserDetails principal = (AdminUserDetails) authentication.getPrincipal();
        // 保存在线信息

        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", securityProperties.getToken().getTokenStartWith() + token);
            put("user", principal);
        }};

        return ResponseEntity.ok(authInfo);
    }
}
