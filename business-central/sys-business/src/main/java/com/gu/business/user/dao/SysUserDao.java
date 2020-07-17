package com.gu.business.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gu.business.user.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

}
