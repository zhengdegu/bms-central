package com.gu.business.user.controller;

import com.gu.business.user.entity.SysQuartzLogEntity;
import com.gu.business.user.service.SysQuartzLogService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 定时任务日志
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@RestController
@RequestMapping("user/sysquartzlog")
public class SysQuartzLogController {
    @Autowired
    private SysQuartzLogService sysQuartzLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysquartzlog:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysQuartzLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{logId}")
    //@RequiresPermissions("user:sysquartzlog:info")
    public R info(@PathVariable("logId") Long logId) {
        SysQuartzLogEntity sysQuartzLog = sysQuartzLogService.getById(logId);

        return R.ok().put("sysQuartzLog", sysQuartzLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysquartzlog:save")
    public R save(@RequestBody SysQuartzLogEntity sysQuartzLog) {
        sysQuartzLogService.save(sysQuartzLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysquartzlog:update")
    public R update(@RequestBody SysQuartzLogEntity sysQuartzLog) {
        sysQuartzLogService.updateById(sysQuartzLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysquartzlog:delete")
    public R delete(@RequestBody Long[] logIds) {
        sysQuartzLogService.removeByIds(Arrays.asList(logIds));

        return R.ok();
    }

}
