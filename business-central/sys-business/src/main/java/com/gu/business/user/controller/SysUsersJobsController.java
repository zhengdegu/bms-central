package com.gu.business.user.controller;

import com.gu.business.user.entity.SysUsersJobsEntity;
import com.gu.business.user.service.SysUsersJobsService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:48
 */
@RestController
@RequestMapping("user/sysusersjobs")
public class SysUsersJobsController {
    @Autowired
    private SysUsersJobsService sysUsersJobsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysusersjobs:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysUsersJobsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("user:sysusersjobs:info")
    public R info(@PathVariable("userId") Long userId) {
        SysUsersJobsEntity sysUsersJobs = sysUsersJobsService.getById(userId);

        return R.ok().put("sysUsersJobs", sysUsersJobs);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysusersjobs:save")
    public R save(@RequestBody SysUsersJobsEntity sysUsersJobs) {
        sysUsersJobsService.save(sysUsersJobs);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysusersjobs:update")
    public R update(@RequestBody SysUsersJobsEntity sysUsersJobs) {
        sysUsersJobsService.updateById(sysUsersJobs);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysusersjobs:delete")
    public R delete(@RequestBody Long[] userIds) {
        sysUsersJobsService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
