package com.gu.business.uua.service.impl;

import com.gu.business.uua.service.UserService;
import com.gu.common.domain.dto.UserDto;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author FastG
 * @date 2020/7/21 9:54
 */
@Slf4j
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {

    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public UserDto selectByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new UserDto();
            }

            @Override
            public List<GrantedAuthority> mapToGrantedAuthorities(UserDto user) {
                log.error("通过用户名查询权限异常:{}", user.getUsername(), throwable);
                return null;
            }

            @Override
            public List<Long> resource(UserDto user) {
                log.error("通过用户名查询数据权限异常:{}", user.getUsername(), throwable);
                return null;
            }
        };
    }
}
