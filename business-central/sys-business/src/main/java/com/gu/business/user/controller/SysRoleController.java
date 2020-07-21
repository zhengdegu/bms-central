package com.gu.business.user.controller;

import com.gu.business.user.entity.SysRoleEntity;
import com.gu.business.user.service.SysRoleService;
import com.gu.common.domain.dto.UserDto;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.redisson.api.RList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 角色表
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
@RestController
@RequestMapping("user/sysrole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysrole:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysRoleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{roleId}")
    //@RequiresPermissions("user:sysrole:info")
    public R info(@PathVariable("roleId") Long roleId) {
        SysRoleEntity sysRole = sysRoleService.getById(roleId);

        return R.ok().put("sysRole", sysRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysrole:save")
    public R save(@RequestBody SysRoleEntity sysRole) {
        sysRoleService.save(sysRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysrole:update")
    public R update(@RequestBody SysRoleEntity sysRole) {
        sysRoleService.updateById(sysRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysrole:delete")
    public R delete(@RequestBody Long[] roleIds) {
        sysRoleService.removeByIds(Arrays.asList(roleIds));

        return R.ok();
    }

}
