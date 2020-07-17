package com.gu.business.user.controller;

import com.gu.business.user.entity.SysDictDetailEntity;
import com.gu.business.user.service.SysDictDetailService;
import com.gu.common.utils.PageUtils;
import com.gu.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 数据字典详情
 *
 * @author FastG
 * @email guzhende@ict.ac.cn
 * @date 2020-07-17 11:22:49
 */
@RestController
@RequestMapping("user/sysdictdetail")
public class SysDictDetailController {
    @Autowired
    private SysDictDetailService sysDictDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:sysdictdetail:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDictDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{detailId}")
    //@RequiresPermissions("user:sysdictdetail:info")
    public R info(@PathVariable("detailId") Long detailId) {
        SysDictDetailEntity sysDictDetail = sysDictDetailService.getById(detailId);

        return R.ok().put("sysDictDetail", sysDictDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("user:sysdictdetail:save")
    public R save(@RequestBody SysDictDetailEntity sysDictDetail) {
        sysDictDetailService.save(sysDictDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:sysdictdetail:update")
    public R update(@RequestBody SysDictDetailEntity sysDictDetail) {
        sysDictDetailService.updateById(sysDictDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("user:sysdictdetail:delete")
    public R delete(@RequestBody Long[] detailIds) {
        sysDictDetailService.removeByIds(Arrays.asList(detailIds));

        return R.ok();
    }

}
