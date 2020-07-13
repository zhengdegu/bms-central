package com.gu.bms.security.security;

import com.gu.bms.security.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 * @author FastG
 * @create 2020-07-13
 **/
@Component
@Slf4j
public  class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final JwtToken jwtToken;
    private final SecurityProperties securityProperties;
    private final UserDetailsService userDetailsService;
    public JwtAuthenticationTokenFilter(JwtToken jwtToken,
                                        SecurityProperties securityProperties,
                                        UserDetailsService userDetailsService){
        this.jwtToken=jwtToken;
        this.securityProperties=securityProperties;
        this.userDetailsService=userDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) httpServletRequest;
        String token = this.resolveToken(servletRequest);
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(token)) {
            String username = jwtToken.getUserNameFromToken(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // Token 续期
                jwtToken.checkRenewal(token);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 获取token
     *
     * @param request 请求
     * @return token
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(securityProperties.getToken().getHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(securityProperties.getToken().getTokenStartWith())) {
            // 去掉令牌前缀
            return bearerToken.replace(securityProperties.getToken().getTokenStartWith(), "");
        } else {
            log.debug("非法Token：{}", bearerToken);
        }
        return null;
    }
}
