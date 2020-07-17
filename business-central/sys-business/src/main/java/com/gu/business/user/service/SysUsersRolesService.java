package com.gu.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gu.business.user.entity.SysUsersRolesEntity;
import com.gu.common.utils.PageUtils;

import java.util.Map;

/**
 * 用户角色关联
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
public interface SysUsersRolesService extends IService<SysUsersRolesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

