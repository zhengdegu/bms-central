package com.gu.business.user.service;

import com.gu.common.domain.dto.UserDto;

import java.util.List;

/**
 * @author FastG
 * @date 2020/7/14 14:33
 */
public interface DataService {

    /**
     * 获取数据权限
     *
     * @param user /
     * @return /
     */
    List<Long> getDeptIds(UserDto user);
}
