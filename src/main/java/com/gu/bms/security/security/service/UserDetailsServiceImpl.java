package com.gu.bms.security.security.service;

import com.gu.bms.common.exception.BadRequestException;
import com.gu.bms.common.exception.EntityNotFoundException;
import com.gu.bms.security.security.service.dto.AdminUserDetails;
import com.gu.bms.security.service.DataService;
import com.gu.bms.security.service.RoleService;
import com.gu.bms.security.service.UserService;
import com.gu.bms.security.service.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author FastG
 * @date 2020/7/13 17:17
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private DataService dataService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUserDetails adminUserDetails=null;
        UserDto user;
        try {
            user = userService.findByName(username);
        } catch (EntityNotFoundException e) {
            // SpringSecurity会自动转换UsernameNotFoundException为BadCredentialsException
            throw new UsernameNotFoundException("", e);
        }
        if (user == null) {
            throw new UsernameNotFoundException("");
        } else {
            if (!user.getEnabled()) {
                throw new BadRequestException("账号未激活");
            }
            adminUserDetails = new AdminUserDetails(user,
                    dataService.getDeptIds(user),
                    roleService.mapToGrantedAuthorities(user)
            );
        }
        return adminUserDetails;
    }
}
