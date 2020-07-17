package com.gu.business.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gu.business.user.entity.SysRolesDeptsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色部门关联
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
@Mapper
public interface SysRolesDeptsDao extends BaseMapper<SysRolesDeptsEntity> {

}
