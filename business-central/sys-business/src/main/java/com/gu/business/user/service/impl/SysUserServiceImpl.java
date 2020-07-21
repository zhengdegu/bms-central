package com.gu.business.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gu.business.user.dao.*;
import com.gu.business.user.entity.*;
import com.gu.business.user.service.SysUserService;
import com.gu.common.domain.dto.DeptDto;
import com.gu.common.domain.dto.JobDto;
import com.gu.common.domain.dto.RoleDto;
import com.gu.common.domain.dto.UserDto;
import com.gu.common.exception.EntityNotFoundException;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Resource
    private SysUserDao userDao;

    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    private SysJobDao sysJobDao;

    @Resource
    private SysDeptDao sysDeptDao;

    @Resource
    private SysMenuDao sysMenuDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysUserEntity> page = this.page(
                new Query<SysUserEntity>().getPage(params),
                new QueryWrapper<SysUserEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public UserDto findByName(String username) {

        SysUserEntity user = userDao.findByName(username);
        if (user == null) {
            throw new EntityNotFoundException(SysUserEntity.class, "user", username);
        }
        UserDto userDto = new UserDto();
        BeanUtil.copyProperties(user, userDto);

        Set<SysRoleEntity> roles = sysRoleDao.findRolesByUserId(user.getUserId());
        Set<RoleDto> roleDtos = new HashSet<>();
        //暴力转换
        roles.forEach(role ->{
            RoleDto roleDto =new RoleDto();
            BeanUtil.copyProperties(role,roleDto);
            roleDtos.add(roleDto);
        });
        userDto.setRoles(roleDtos);

        Set<SysJobEntity> jobs = sysJobDao.findJobsByUserId(user.getUserId());
        Set<JobDto> jobDtos = new HashSet<>();
        jobs.forEach(job ->{
            JobDto jobDto =new JobDto();
            BeanUtil.copyProperties(job,jobDto);
            jobDtos.add(jobDto);
        });
        userDto.setJobs(jobDtos);

//        SysDeptEntity sysDeptEntity = sysDeptDao.selectById(user.getDeptId());
//        DeptDto deptDto = new DeptDto();
//        BeanUtil.copyProperties(sysDeptEntity, deptDto);
//        userDto.setDept(deptDto);

        return userDto;
    }

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDto user) {
        Set<String> permissions = new HashSet<>();
        if (user.getIsAdmin()) {
            permissions.add("admin");
        }
        //根据角色查询权限
        List<SysMenuEntity> menusByRoleIds = sysMenuDao.findMenusByRoleIds(user.getRoles()
                .stream()
                .map(RoleDto::getId)
                .collect(Collectors.toList()));
        permissions = menusByRoleIds.stream()
                .map(SysMenuEntity::getPermission)
                .collect(Collectors.toSet());
        return permissions.
                stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


}