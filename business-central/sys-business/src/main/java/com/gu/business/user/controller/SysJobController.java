package com.gu.business.user.controller;

import com.gu.business.user.entity.SysJobEntity;
import com.gu.business.user.service.SysJobService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 岗位
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@RestController
@RequestMapping("user/sysjob")
public class SysJobController {
    @Autowired
    private SysJobService sysJobService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysjob:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysJobService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{jobId}")
    //@RequiresPermissions("user:sysjob:info")
    public R info(@PathVariable("jobId") Long jobId) {
        SysJobEntity sysJob = sysJobService.getById(jobId);

        return R.ok().put("sysJob", sysJob);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysjob:save")
    public R save(@RequestBody SysJobEntity sysJob) {
        sysJobService.save(sysJob);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysjob:update")
    public R update(@RequestBody SysJobEntity sysJob) {
        sysJobService.updateById(sysJob);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysjob:delete")
    public R delete(@RequestBody Long[] jobIds) {
        sysJobService.removeByIds(Arrays.asList(jobIds));

        return R.ok();
    }

}
