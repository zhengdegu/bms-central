package com.gu.business.user.service.impl;

import com.gu.business.user.service.SysUserService;
import com.gu.common.domain.dto.UserDto;
import com.gu.common.properties.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author FastG
 * @date 2020/7/21 9:21
 */

//public class UserDetailsServiceImpl implements UserDetailsService {
//
//
//    @Autowired
//    private SysUserService sysUserService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        UserDto userDto = sysUserService.findByName(username);
//        if (userDto == null) {
//            throw new UsernameNotFoundException("用户名或密码错误");
//        }
//        List<GrantedAuthority> grantedAuthorities = sysUserService.mapToGrantedAuthorities(userDto);
//        return new UserDetailsDto(userDto,
//                grantedAuthorities);
//    }
//}
