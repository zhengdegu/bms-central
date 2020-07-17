package com.gu.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gu.business.user.entity.SysJobEntity;
import com.gu.common.utils.PageUtils;

import java.util.Map;

/**
 * 岗位
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
public interface SysJobService extends IService<SysJobEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

