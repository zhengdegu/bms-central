package com.gu.business.user.controller;

import com.gu.business.user.entity.SysRolesDeptsEntity;
import com.gu.business.user.service.SysRolesDeptsService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 角色部门关联
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
@RestController
@RequestMapping("user/sysrolesdepts")
public class SysRolesDeptsController {
    @Autowired
    private SysRolesDeptsService sysRolesDeptsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysrolesdepts:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysRolesDeptsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{roleId}")
    //@RequiresPermissions("user:sysrolesdepts:info")
    public R info(@PathVariable("roleId") Long roleId) {
        SysRolesDeptsEntity sysRolesDepts = sysRolesDeptsService.getById(roleId);

        return R.ok().put("sysRolesDepts", sysRolesDepts);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysrolesdepts:save")
    public R save(@RequestBody SysRolesDeptsEntity sysRolesDepts) {
        sysRolesDeptsService.save(sysRolesDepts);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysrolesdepts:update")
    public R update(@RequestBody SysRolesDeptsEntity sysRolesDepts) {
        sysRolesDeptsService.updateById(sysRolesDepts);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysrolesdepts:delete")
    public R delete(@RequestBody Long[] roleIds) {
        sysRolesDeptsService.removeByIds(Arrays.asList(roleIds));

        return R.ok();
    }

}
