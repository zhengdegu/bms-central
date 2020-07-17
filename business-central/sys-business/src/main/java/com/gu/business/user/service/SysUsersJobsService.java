package com.gu.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gu.business.user.entity.SysUsersJobsEntity;
import com.gu.common.utils.PageUtils;

import java.util.Map;

/**
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
public interface SysUsersJobsService extends IService<SysUsersJobsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

