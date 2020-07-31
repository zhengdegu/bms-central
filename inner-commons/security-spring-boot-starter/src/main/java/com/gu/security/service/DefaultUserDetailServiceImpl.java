package com.gu.security.service;

import com.gu.common.domain.dto.UserDto;
import com.gu.common.properties.dto.UserDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author FastG
 * @create 2020-07-17
 **/

@Slf4j
public class DefaultUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = new UserDto();
        userDto.setUsername("admin");
        userDto.setEnabled(true);
        userDto.setPassword(passwordEncoder.encode("123456"));

        return new UserDetailsDto(userDto, null,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        //return new User(username, passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
