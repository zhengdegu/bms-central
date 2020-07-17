package com.gu.business.user.controller;

import com.gu.business.user.entity.SysLogEntity;
import com.gu.business.user.service.SysLogService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 系统日志
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@RestController
@RequestMapping("user/syslog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:syslog:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{logId}")
    //@RequiresPermissions("user:syslog:info")
    public R info(@PathVariable("logId") Long logId) {
        SysLogEntity sysLog = sysLogService.getById(logId);

        return R.ok().put("sysLog", sysLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:syslog:save")
    public R save(@RequestBody SysLogEntity sysLog) {
        sysLogService.save(sysLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:syslog:update")
    public R update(@RequestBody SysLogEntity sysLog) {
        sysLogService.updateById(sysLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:syslog:delete")
    public R delete(@RequestBody Long[] logIds) {
        sysLogService.removeByIds(Arrays.asList(logIds));

        return R.ok();
    }

}
