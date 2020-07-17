package com.gu.business.user.controller;

import com.gu.business.user.entity.SysUsersRolesEntity;
import com.gu.business.user.service.SysUsersRolesService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 用户角色关联
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
@RestController
@RequestMapping("user/sysusersroles")
public class SysUsersRolesController {
    @Autowired
    private SysUsersRolesService sysUsersRolesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysusersroles:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysUsersRolesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("user:sysusersroles:info")
    public R info(@PathVariable("userId") Long userId) {
        SysUsersRolesEntity sysUsersRoles = sysUsersRolesService.getById(userId);

        return R.ok().put("sysUsersRoles", sysUsersRoles);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysusersroles:save")
    public R save(@RequestBody SysUsersRolesEntity sysUsersRoles) {
        sysUsersRolesService.save(sysUsersRoles);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysusersroles:update")
    public R update(@RequestBody SysUsersRolesEntity sysUsersRoles) {
        sysUsersRolesService.updateById(sysUsersRoles);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysusersroles:delete")
    public R delete(@RequestBody Long[] userIds) {
        sysUsersRolesService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
