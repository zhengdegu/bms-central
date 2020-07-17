package com.gu.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gu.business.user.entity.SysRolesMenusEntity;
import com.gu.common.utils.PageUtils;

import java.util.Map;

/**
 * 角色菜单关联
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
public interface SysRolesMenusService extends IService<SysRolesMenusEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

