package com.gu.business.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gu.business.user.entity.SysQuartzJobEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@Mapper
public interface SysQuartzJobDao extends BaseMapper<SysQuartzJobEntity> {

}