package com.gu.business.user.controller;

import com.gu.business.user.entity.SysMenuEntity;
import com.gu.business.user.service.SysMenuService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 系统菜单
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@RestController
@RequestMapping("user/sysmenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysmenu:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysMenuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{menuId}")
    //@RequiresPermissions("user:sysmenu:info")
    public R info(@PathVariable("menuId") Long menuId) {
        SysMenuEntity sysMenu = sysMenuService.getById(menuId);

        return R.ok().put("sysMenu", sysMenu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysmenu:save")
    public R save(@RequestBody SysMenuEntity sysMenu) {
        sysMenuService.save(sysMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysmenu:update")
    public R update(@RequestBody SysMenuEntity sysMenu) {
        sysMenuService.updateById(sysMenu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysmenu:delete")
    public R delete(@RequestBody Long[] menuIds) {
        sysMenuService.removeByIds(Arrays.asList(menuIds));

        return R.ok();
    }

}
