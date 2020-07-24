package com.gu.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gu.business.user.entity.SysUserEntity;
import com.gu.common.domain.dto.UserDto;
import com.gu.common.utils.PageUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
public interface SysUserService extends IService<SysUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户名查找用户
     *
     * @param username
     */
    UserDto findByName(String username);

    /**
     * 获取用户权限
     *
     * @param user
     * @return
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDto user);
}

