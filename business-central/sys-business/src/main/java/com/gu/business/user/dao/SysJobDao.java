package com.gu.business.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gu.business.user.entity.SysJobEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * 岗位
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@Mapper
public interface SysJobDao extends BaseMapper<SysJobEntity> {


    @Select("select*from sys_job where job_id in (select job_id from sys_users_jobs where user_id =#{userId})")
    Set<SysJobEntity> findJobsByUserId(@Param("userId") Long userId);
}
