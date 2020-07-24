package com.gu.business.uua.service;

import com.gu.business.uua.service.impl.UserServiceFallbackFactory;
import com.gu.common.constat.ServiceNameConstants;
import com.gu.common.domain.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    @GetMapping(value = "/api/users/{username}")
    UserDto selectByUsername(@PathVariable("username") String username);

    /**
     * 获取用户权限信息
     *
     * @param user 用户信息
     * @return 权限信息
     */
    @PostMapping(value = "/api/users/auth")
    List<GrantedAuthority> mapToGrantedAuthorities(@RequestBody UserDto user);

    @PostMapping(value = "/api/users/resource")
    public List<Long> resource(@RequestBody UserDto user);
}
