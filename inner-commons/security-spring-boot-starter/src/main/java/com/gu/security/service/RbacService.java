package com.gu.security.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author FastG
 * @date 2020/7/20 13:46
 */
public interface RbacService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
