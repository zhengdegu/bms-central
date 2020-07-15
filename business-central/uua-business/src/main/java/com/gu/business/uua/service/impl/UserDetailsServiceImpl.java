package com.gu.business.uua.service.impl;

import com.gu.business.uua.service.dto.UserDetailsDto;
import com.gu.common.domain.dto.UserDto;
import com.gu.common.exception.BadRequestException;
import com.gu.common.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author FastG
 * @date 2020/7/15 16:14
 */
public class UserDetailsServiceImpl implements UserDetailsService {



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserDto userDto;
        UserDetailsDto userDetailsDto;
        try {
//            userDto  = userService.findByName(username);
            userDto =new UserDto();
            userDto.setNickName("admin");
            userDto.setPassword(passwordEncoder.encode("123456"));
        } catch (EntityNotFoundException e) {
            // SpringSecurity会自动转换UsernameNotFoundException为BadCredentialsException
            throw new UsernameNotFoundException("", e);
        }
        if (userDto == null) {
            throw new UsernameNotFoundException("");
        } else {
            if (!userDto.getEnabled()) {
                throw new BadRequestException("账号未激活");
            }
            userDetailsDto =new UserDetailsDto(
             userDto,
            null,
                    null
            );
        }
        return userDetailsDto;
    }
}
