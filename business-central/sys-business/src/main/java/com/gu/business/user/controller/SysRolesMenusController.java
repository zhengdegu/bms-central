package com.gu.business.user.controller;

import com.gu.business.user.entity.SysRolesMenusEntity;
import com.gu.business.user.service.SysRolesMenusService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 角色菜单关联
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
@RestController
@RequestMapping("user/sysrolesmenus")
public class SysRolesMenusController {
    @Autowired
    private SysRolesMenusService sysRolesMenusService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysrolesmenus:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysRolesMenusService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{menuId}")
    //@RequiresPermissions("user:sysrolesmenus:info")
    public R info(@PathVariable("menuId") Long menuId) {
        SysRolesMenusEntity sysRolesMenus = sysRolesMenusService.getById(menuId);

        return R.ok().put("sysRolesMenus", sysRolesMenus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysrolesmenus:save")
    public R save(@RequestBody SysRolesMenusEntity sysRolesMenus) {
        sysRolesMenusService.save(sysRolesMenus);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysrolesmenus:update")
    public R update(@RequestBody SysRolesMenusEntity sysRolesMenus) {
        sysRolesMenusService.updateById(sysRolesMenus);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysrolesmenus:delete")
    public R delete(@RequestBody Long[] menuIds) {
        sysRolesMenusService.removeByIds(Arrays.asList(menuIds));

        return R.ok();
    }

}
