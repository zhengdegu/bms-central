package com.gu.security.service;

import com.gu.common.properties.SecurityConstants;
import com.gu.common.properties.SecurityProperties;
import com.gu.common.properties.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @author FastG
 * @date 2020/7/20 13:48
 */
public class DefaultRbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 前端跨域OPTIONS请求预检放行 也可通过前端配置代理实现
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetailsDto
                    && ((UserDetailsDto) principal).getUsername().equals(SecurityConstants.ADMIN_USER_NAME)) {
                return true;

            }
        }

        return false;
    }


}
