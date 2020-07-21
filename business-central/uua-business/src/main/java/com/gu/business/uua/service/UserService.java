package com.gu.business.uua.service;

import com.gu.business.uua.service.impl.UserServiceFallbackFactory;
import com.gu.common.constat.ServiceNameConstants;
import com.gu.common.domain.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 查询用户openfegin
 *
 * @author FastG
 * @date 2020/7/21 9:51
 */
@FeignClient(name = ServiceNameConstants.USER_SERVICE, fallbackFactory = UserServiceFallbackFactory.class, decode404 = true)
public interface UserService {

    /**
     * feign rpc访问远程/users/{username}接口
     * 查询用户实体对象SysUser
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/user/{username}")
    UserDto selectByUsername(@PathVariable("username") String username);

    /**
     * 获取用户权限信息
     * @param user 用户信息
     * @return 权限信息
     */
    @PostMapping(value = "/user/auth")
    List<GrantedAuthority> mapToGrantedAuthorities(@RequestBody UserDto user);


}
