package com.gu.business.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gu.business.user.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;

/**
 * 角色表
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    @Select("select *from sys_role where role_id in (select role_id from sys_users_roles where user_id =#{userId})")
    Set<SysRoleEntity> findRolesByUserId(@Param("userId") Long userId);
}
