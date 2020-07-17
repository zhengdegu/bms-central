package com.gu.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gu.business.user.entity.SysDeptEntity;
import com.gu.common.utils.PageUtils;

import java.util.Map;

/**
 * 部门
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:50
 */
public interface SysDeptService extends IService<SysDeptEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

