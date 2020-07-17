package com.gu.business.user.controller;

import com.gu.business.user.entity.SysQuartzJobEntity;
import com.gu.business.user.service.SysQuartzJobService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 定时任务
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@RestController
@RequestMapping("user/sysquartzjob")
public class SysQuartzJobController {
    @Autowired
    private SysQuartzJobService sysQuartzJobService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysquartzjob:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysQuartzJobService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{jobId}")
    //@RequiresPermissions("user:sysquartzjob:info")
    public R info(@PathVariable("jobId") Long jobId) {
        SysQuartzJobEntity sysQuartzJob = sysQuartzJobService.getById(jobId);

        return R.ok().put("sysQuartzJob", sysQuartzJob);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysquartzjob:save")
    public R save(@RequestBody SysQuartzJobEntity sysQuartzJob) {
        sysQuartzJobService.save(sysQuartzJob);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysquartzjob:update")
    public R update(@RequestBody SysQuartzJobEntity sysQuartzJob) {
        sysQuartzJobService.updateById(sysQuartzJob);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysquartzjob:delete")
    public R delete(@RequestBody Long[] jobIds) {
        sysQuartzJobService.removeByIds(Arrays.asList(jobIds));

        return R.ok();
    }

}
