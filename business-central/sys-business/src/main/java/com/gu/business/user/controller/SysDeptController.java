package com.gu.business.user.controller;

import com.gu.business.user.entity.SysDeptEntity;
import com.gu.business.user.service.SysDeptService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 部门
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:50
 */
@RestController
@RequestMapping("user/sysdept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysdept:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDeptService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{deptId}")
    //@RequiresPermissions("user:sysdept:info")
    public R info(@PathVariable("deptId") Long deptId) {
        SysDeptEntity sysDept = sysDeptService.getById(deptId);

        return R.ok().put("sysDept", sysDept);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysdept:save")
    public R save(@RequestBody SysDeptEntity sysDept) {
        sysDeptService.save(sysDept);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysdept:update")
    public R update(@RequestBody SysDeptEntity sysDept) {
        sysDeptService.updateById(sysDept);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysdept:delete")
    public R delete(@RequestBody Long[] deptIds) {
        sysDeptService.removeByIds(Arrays.asList(deptIds));

        return R.ok();
    }

}
