package com.gu.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gu.business.user.entity.SysQuartzLogEntity;
import com.gu.common.utils.PageUtils;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
public interface SysQuartzLogService extends IService<SysQuartzLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

