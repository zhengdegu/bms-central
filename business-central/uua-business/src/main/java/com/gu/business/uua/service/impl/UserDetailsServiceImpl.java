package com.gu.business.uua.service.impl;

import com.gu.business.uua.service.UserService;
import com.gu.common.domain.dto.UserDto;
import com.gu.common.properties.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

/**
 * @author FastG
 * @date 2020/7/21 9:21
 */

public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = userService.selectByUsername(username);
        if (userDto == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        //List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<GrantedAuthority> grantedAuthorities = userService.mapToGrantedAuthorities(userDto);
        List<Long> resource = userService.resource(userDto);
        return new UserDetailsDto(userDto,resource,
                grantedAuthorities);
    }
}
