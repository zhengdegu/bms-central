package com.gu.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gu.business.user.entity.SysDictDetailEntity;
import com.gu.common.utils.PageUtils;

import java.util.Map;

/**
 * 数据字典详情
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
public interface SysDictDetailService extends IService<SysDictDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

