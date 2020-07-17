package com.gu.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gu.business.user.entity.SysRolesDeptsEntity;
import com.gu.common.utils.PageUtils;

import java.util.Map;

/**
 * 角色部门关联
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
public interface SysRolesDeptsService extends IService<SysRolesDeptsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

