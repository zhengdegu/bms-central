package com.gu.bms.security.security;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.gu.bms.redis.utils.RedisUtil;
import com.gu.bms.security.security.properties.SecurityProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * TOKEN实现
 *
 * @author FastG
 * @date 2020/7/13 15:32
 */
@Slf4j
@Component
public class JwtToken implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";
    private final SecurityProperties securityProperties;
    private final RedisUtil redisUtil;
    private JwtParser jwtParser;
    private JwtBuilder jwtBuilder;

    public JwtToken(SecurityProperties securityProperties, RedisUtil redisUtil) {
        this.securityProperties = securityProperties;
        this.redisUtil = redisUtil;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(securityProperties.getToken().getBase64Secret());
        Key key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        jwtBuilder = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS512);
    }

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Authentication authentication) {
        /*
         * 获取权限列表
         */
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return jwtBuilder
                // 加入ID确保生成的 Token 都不一致
                .setId(IdUtil.simpleUUID())
                .claim(AUTHORITIES_KEY, authorities)
                .setSubject(authentication.getName())
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    public Claims getClaims(String token) {
        Claims claims = null;
        try {
            claims = jwtParser
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }


    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaims(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * @param token 需要检查的token
     */
    public void checkRenewal(String token) {
        // 判断是否续期token,计算token的过期时间
        long time = redisUtil.getExpire(securityProperties.getToken().getOnlineKey() + token) * 1000;
        Date expireDate = DateUtil.offset(new Date(), DateField.MILLISECOND, (int) time);
        // 判断当前时间与过期时间的时间差
        long differ = expireDate.getTime() - System.currentTimeMillis();
        // 如果在续期检查的范围内，则续期
        if (differ <= securityProperties.getToken().getDetect()) {
            long renew = time + securityProperties.getToken().getRenew();
            redisUtil.expire(securityProperties.getToken().getOnlineKey() + token, renew);
        }
    }

    /**
     * 从header中获取token
     *
     * @param request 请求
     * @return token
     */
    public String getToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(securityProperties.getToken().getHeader());
        if (requestHeader != null && requestHeader.startsWith(securityProperties.getToken().getTokenStartWith())) {
            return requestHeader.substring(7);
        }
        return null;
    }
}
