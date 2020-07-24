package com.gu.business.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gu.business.user.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统菜单
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {


    @Select("<script> select distinct t.* from sys_menu t  inner join sys_roles_menus r on r.menu_id = t.menu_id  where" +
            "    r.role_id in" +
            " <foreach item='roleIds' index='index' collection='list' open='(' separator=',' close=')'> " +
            "#{roleIds}" +
            " </foreach>" +
            "</script>")
    List<SysMenuEntity> findMenusByRoleIds(List<Long> roleIds);
}
